import React from 'react';
import Header from './components/Header';
import Sidebar from './components/Sidebar';
import Content from './components/Content';
import './App.css';
import {BrowserRouter} from 'react-router-dom';


function App() {
  return (
    <BrowserRouter>
      <div className="app">
        <Header />
        <Content />
        <Sidebar />
      </div>
    </BrowserRouter>
  );
}

export default App;
