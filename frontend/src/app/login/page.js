'use client'
import React from "react";
import { useForm } from "react-hook-form";
import Link from "next/link";
import axios from 'axios';
import Cookies from 'js-cookie';

export default function page() {
  const { register, handleSubmit, formState: { errors } } = useForm();

  const onSubmit = async (data) => {
    const { username, password } = data;
  
    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        username: username,
        password: password,
      });
  
      // Assuming the response includes the user data you need (update accordingly)
      if (response.status === 200) {
        console.log("Login successful", response.data);
  
        // Set cookies with login tokens or user data. Adjust according to your API response
        // For example, assuming response.data contains a token and userId
        Cookies.set('currentUser', response.data.token, { expires: 1 }); // Expires in 1 day
  
        // Redirect the user after setting the cookies
        console.log('logged in')
      }
    } catch (error) {
      console.error("Login error", error.response ? error.response.data : error);
      // Optionally, show an error message to your user
      // For example, using a state hook to set and display the error message in your component
    }
  };

  return (
    <main className="flex justify-center items-center w-[100vw] h-[100vh]">
      <form onSubmit={handleSubmit(onSubmit)} className="bg-white rounded-xl p-2 w-[500px] flex flex-col gap-5">
        <div className="formGroup flex flex-col">
          <label htmlFor="username" className="text-dark">Username:</label>
          <input {...register("username", { required: true })} type="text" id="username" className="rounded-xl bg-light p-2"/>
          {errors.username && <span>This field is required</span>}
        </div>
        <div className="formGroup flex flex-col">
          <label htmlFor="password" className="text-dark">Password:</label>
          <input {...register("password", { required: true })} type="password" id="password" className="rounded-xl bg-light p-2"/>
          {errors.password && <span>This field is required</span>}
        </div>
        <button className="p-2 bg-dark text-white font-bold rounded-xl" type="submit">Login</button>
        <Link href="/register" className="underline text-dark text-sm text-center">Don't have an account? Register now!</Link>
      </form>
    </main>
  );
}

