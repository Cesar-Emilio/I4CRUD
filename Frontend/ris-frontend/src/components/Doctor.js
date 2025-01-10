import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Doctor = () => {
  const [doctors, setDoctors] = useState([]); // Inicializamos como array vacío
  const [name, setName] = useState('');
  const [lastName, setLastName] = useState('');
  const [curp, setCurp] = useState('');
  const [phone, setPhone] = useState('');
  const [age, setAge] = useState('');
  const [speciality, setSpeciality] = useState('');
  const [status, setStatus] = useState(true);

  useEffect(() => {
    fetchDoctors();
  }, []);

  const fetchDoctors = async () => {
    try {
      const response = await axios.get('http://localhost:8080/doctor/all');
      setDoctors(response.data.data || []); // Aseguramos que no sea undefined
    } catch (error) {
      console.error('Error fetching doctors:', error);
    }
  };

  const handleAddDoctor = async () => {
    const doctorData = { name, lastName, curp, phone, age: parseInt(age), speciality, status };
    try {
      const response = await axios.post('http://localhost:8080/doctor/add', doctorData);
      console.log('Doctor added:', response.data);
      fetchDoctors();
      clearForm();
    } catch (error) {
      console.error('Error adding doctor:', error);
    }
  };

  const handleDeleteDoctor = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/doctor/delete/${id}`);
      fetchDoctors();
    } catch (error) {
      console.error('Error deleting doctor:', error);
    }
  };

  const handleEditDoctor = (doctor) => {
    setName(doctor.name);
    setLastName(doctor.lastName);
    setCurp(doctor.curp);
    setPhone(doctor.phone);
    setAge(doctor.age);
    setSpeciality(doctor.speciality);
    setStatus(doctor.status);
  };

  const clearForm = () => {
    setName('');
    setLastName('');
    setCurp('');
    setPhone('');
    setAge('');
    setSpeciality('');
    setStatus(true);
  };

  return (
    <div>
      <h2>Doctors</h2>

      <form onSubmit={(e) => e.preventDefault()}>
        <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
        <input type="text" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} />
        <input type="text" placeholder="CURP" value={curp} onChange={(e) => setCurp(e.target.value)} />
        <input type="text" placeholder="Phone" value={phone} onChange={(e) => setPhone(e.target.value)} />
        <input type="number" placeholder="Age" value={age} onChange={(e) => setAge(e.target.value)} />
        <input type="text" placeholder="Specialty" value={speciality} onChange={(e) => setSpeciality(e.target.value)} />
        <label>
          Active:
          <input type="checkbox" checked={status} onChange={() => setStatus(!status)} />
        </label>
        <button type="button" onClick={handleAddDoctor}>Add Doctor</button>
      </form>

      <ul>
        {doctors && doctors.length > 0 ? (
          doctors.map((doctor) => (
            <li key={doctor.id}>
              {doctor.name} {doctor.lastName} - {doctor.speciality}
              <button onClick={() => handleEditDoctor(doctor)}>Edit</button>
              <button onClick={() => handleDeleteDoctor(doctor.id)}>Delete</button>
            </li>
          ))
        ) : (
          <li>No doctors available</li>
        )}
      </ul>
    </div>
  );
};

export default Doctor;
