import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Supply = () => {
  const [supplies, setSupplies] = useState([]); // Inicializamos como array vacío
  const [name, setName] = useState('');
  const [quantity, setQuantity] = useState('');
  const [price, setPrice] = useState('');

  useEffect(() => {
    fetchSupplies();
  }, []);

  const fetchSupplies = async () => {
    try {
      const response = await axios.get('http://localhost:8080/supply/all');
      setSupplies(response.data.data || []); // Aseguramos que no sea undefined
    } catch (error) {
      console.error('Error fetching supplies:', error);
    }
  };

  const handleAddSupply = async () => {
    const supplyData = { name, quantity: parseInt(quantity), price: parseFloat(price) };
    try {
      const response = await axios.post('http://localhost:8080/supply/add', supplyData);
      console.log('Supply added:', response.data);
      fetchSupplies();
      clearForm();
    } catch (error) {
      console.error('Error adding supply:', error);
    }
  };

  const handleDeleteSupply = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/supply/delete/${id}`);
      fetchSupplies();
    } catch (error) {
      console.error('Error deleting supply:', error);
    }
  };

  const handleEditSupply = (supply) => {
    setName(supply.name);
    setQuantity(supply.quantity);
    setPrice(supply.price);
  };

  const clearForm = () => {
    setName('');
    setQuantity('');
    setPrice('');
  };

  return (
    <div>
      <h2>Supplies</h2>

      <form onSubmit={(e) => e.preventDefault()}>
        <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
        <input type="number" placeholder="Quantity" value={quantity} onChange={(e) => setQuantity(e.target.value)} />
        <input type="number" step="0.01" placeholder="Price" value={price} onChange={(e) => setPrice(e.target.value)} />
        <button type="button" onClick={handleAddSupply}>Add Supply</button>
      </form>

      <ul>
        {supplies && supplies.length > 0 ? (
          supplies.map((supply) => (
            <li key={supply.id}>
              {supply.name} - Quantity: {supply.quantity}, Price: {supply.price}
              <button onClick={() => handleEditSupply(supply)}>Edit</button>
              <button onClick={() => handleDeleteSupply(supply.id)}>Delete</button>
            </li>
          ))
        ) : (
          <li>No supplies available</li>
        )}
      </ul>
    </div>
  );
};

export default Supply;
