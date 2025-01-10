import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const getAllDoctors = () => axios.get(`${API_URL}/doctor/all`);
export const addDoctor = (doctor) => axios.post(`${API_URL}/doctor/add`, doctor);
export const updateDoctor = (id, doctor) => axios.put(`${API_URL}/doctor/update/${id}`, doctor);
export const deleteDoctor = (id) => axios.delete(`${API_URL}/doctor/delete/${id}`);

export const getAllOffices = () => axios.get(`${API_URL}/office/all`);
export const addOffice = (office) => axios.post(`${API_URL}/office/add`, office);
export const updateOffice = (id, office) => axios.put(`${API_URL}/office/update/${id}`, office);
export const deleteOffice = (id) => axios.delete(`${API_URL}/office/delete/${id}`);

export const getAllPatients = () => axios.get(`${API_URL}/patient/all`);
export const savePatient = (patient) => axios.post(`${API_URL}/patient/save`, patient);
export const updatePatient = (patient) => axios.put(`${API_URL}/patient/update`, patient);
export const changeStatus = (patient) => axios.put(`${API_URL}/patient/changeStatus`, patient);

export const getAllSupplies = () => axios.get(`${API_URL}/supply/all`);
export const addSupply = (supply) => axios.post(`${API_URL}/supply/add`, supply);
export const updateSupply = (id, supply) => axios.put(`${API_URL}/supply/update/${id}`, supply);
export const deleteSupply = (id) => axios.delete(`${API_URL}/supply/delete/${id}`);
