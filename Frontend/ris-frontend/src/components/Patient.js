import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Patient = () => {
  const [patients, setPatients] = useState([]); // Inicializamos como array vacío
  const [name, setName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phone, setPhone] = useState('');
  const [status, setStatus] = useState(true);

  useEffect(() => {
    fetchPatients();
  }, []);

  const fetchPatients = async () => {
    try {
      const response = await axios.get('http://localhost:8080/patient/all');
      setPatients(response.data.data || []); // Aseguramos que no sea undefined
    } catch (error) {
      console.error('Error fetching patients:', error);
    }
  };

  const handleAddPatient = async () => {
    const patientData = { name, lastName, phone, status };
    try {
      const response = await axios.post('http://localhost:8080/patient/save', patientData);
      console.log('Patient added:', response.data);
      fetchPatients();
      clearForm();
    } catch (error) {
      console.error('Error adding patient:', error);
    }
  };

  const handleDeletePatient = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/patient/delete/${id}`);
      fetchPatients();
    } catch (error) {
      console.error('Error deleting patient:', error);
    }
  };

  const handleEditPatient = (patient) => {
    setName(patient.name);
    setLastName(patient.lastName);
    setPhone(patient.phone);
    setStatus(patient.status);
  };

  const clearForm = () => {
    setName('');
    setLastName('');
    setPhone('');
    setStatus(true);
  };

  return (
    <div>
      <h2>Patients</h2>

      <form onSubmit={(e) => e.preventDefault()}>
        <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
        <input type="text" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} />
        <input type="text" placeholder="Phone" value={phone} onChange={(e) => setPhone(e.target.value)} />
        <label>
          Active:
          <input type="checkbox" checked={status} onChange={() => setStatus(!status)} />
        </label>
        <button type="button" onClick={handleAddPatient}>Add Patient</button>
      </form>

      <ul>
        {patients && patients.length > 0 ? (
          patients.map((patient) => (
            <li key={patient.id}>
              {patient.name} {patient.lastName} - {patient.phone}
              <button onClick={() => handleEditPatient(patient)}>Edit</button>
              <button onClick={() => handleDeletePatient(patient.id)}>Delete</button>
            </li>
          ))
        ) : (
          <li>No patients available</li>
        )}
      </ul>
    </div>
  );
};

export default Patient;
