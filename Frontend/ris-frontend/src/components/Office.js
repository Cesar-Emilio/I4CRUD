import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Office = () => {
  const [offices, setOffices] = useState([]); // Inicializamos como array vacío
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [phone, setPhone] = useState('');
  const [status, setStatus] = useState(true);

  useEffect(() => {
    fetchOffices();
  }, []);

  const fetchOffices = async () => {
    try {
      const response = await axios.get('http://localhost:8080/office/all');
      setOffices(response.data.data || []); // Aseguramos que no sea undefined
    } catch (error) {
      console.error('Error fetching offices:', error);
    }
  };

  const handleAddOffice = async () => {
    const officeData = { name, address, phone, status };
    try {
      const response = await axios.post('http://localhost:8080/office/add', officeData);
      console.log('Office added:', response.data);
      fetchOffices();
      clearForm();
    } catch (error) {
      console.error('Error adding office:', error);
    }
  };

  const handleDeleteOffice = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/office/delete/${id}`);
      fetchOffices();
    } catch (error) {
      console.error('Error deleting office:', error);
    }
  };

  const handleEditOffice = (office) => {
    setName(office.name);
    setAddress(office.address);
    setPhone(office.phone);
    setStatus(office.status);
  };

  const clearForm = () => {
    setName('');
    setAddress('');
    setPhone('');
    setStatus(true);
  };

  return (
    <div>
      <h2>Offices</h2>

      <form onSubmit={(e) => e.preventDefault()}>
        <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
        <input type="text" placeholder="Address" value={address} onChange={(e) => setAddress(e.target.value)} />
        <input type="text" placeholder="Phone" value={phone} onChange={(e) => setPhone(e.target.value)} />
        <label>
          Active:
          <input type="checkbox" checked={status} onChange={() => setStatus(!status)} />
        </label>
        <button type="button" onClick={handleAddOffice}>Add Office</button>
      </form>

      <ul>
        {offices && offices.length > 0 ? (
          offices.map((office) => (
            <li key={office.id}>
              {office.name} - {office.address} - {office.phone}
              <button onClick={() => handleEditOffice(office)}>Edit</button>
              <button onClick={() => handleDeleteOffice(office.id)}>Delete</button>
            </li>
          ))
        ) : (
          <li>No offices available</li>
        )}
      </ul>
    </div>
  );
};

export default Office;
