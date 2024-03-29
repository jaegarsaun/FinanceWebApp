'use client'

import React, { createContext, useContext, useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

const AuthContext = createContext();

export function useAuth() {
    return useContext(AuthContext);
}

export const AuthProvider = ({ children }) => {
    const [currentUser, setCurrentUser] = useState(() => {
        // Initialize currentUser from cookie for SSR/SSG compatibility
        return Cookies.get('currentUser');
    });

    const login = async (username, password) => {
        try {
            const response = await axios.post('http://localhost:8080/auth/login', { username, password });
            // Assume response includes some user identifier or user data
            setCurrentUser(response.data);
            Cookies.set('currentUser', JSON.stringify(response.data), { expires: 1 }); // Expires in 1 day
        } catch (error) {
            console.error('Login failed', error);
            throw error;
        }
    };

    const logout = () => {
        setCurrentUser(null);
        Cookies.remove('currentUser'); // Remove the cookie
        // Redirect to login page
        if (typeof window !== 'undefined') {
            window.location.href = '/login';
        }
    };

    const value = {
        currentUser,
        login,
        logout
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
