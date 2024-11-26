import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

// Import your components


import './App.css';
import Navbar from './components/common/Navbar';
import LoginPage from './components/auth/LoginPage';
import ProfilePage from './components/userspage/ProfilePage';
import RegistrationPage from './components/auth/RegistrationPage';
import UserManagementPage from './components/userspage/UserManagementPage';
import UpdateUser from './components/userspage/UpdateUser';
import FooterComponent from './components/common/Footer';
import UserService from './components/service/UserService';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            <Route exact path="/" element={<LoginPage />} />
            <Route exact path="/login" element={<LoginPage />} />
            <Route path="/profile" element={<ProfilePage />} />

            {/* Check if user is authenticated and admin before rendering admin-only routes */}
            {UserService.adminOnly() && (
              <>
                <Route path="/register" element={<RegistrationPage />} />
                <Route path="/admin/user-management" element={<UserManagementPage />} />
                <Route path="/update-user/:userId" element={<UpdateUser />} />
              </>
            )}
            <Route path="*" element={<Navigate to="/login" />} />
          </Routes>
        </div>
        <FooterComponent />
      </div>
    </BrowserRouter>
  );
}

export default App;