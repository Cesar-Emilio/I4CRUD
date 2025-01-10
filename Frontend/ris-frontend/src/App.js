import React from 'react';
import Doctor from './components/Doctor';
import Office from './components/Office';
import Patient from './components/Patient';
import Supply from './components/Supply';
import './App.css';

const App = () => {
  return (
    <div className="App">
      <h1>CRUD RIS Application</h1>
      <Doctor />
      <Office />
      <Patient />
      <Supply />
    </div>
  );
};

export default App;
