'use client'
import React from "react";
import { useForm } from "react-hook-form";
import Link from "next/link";
import axios from "axios";

export default function RegisterPage() {
  const { register, handleSubmit, watch, formState: { errors } } = useForm();

  const onSubmit = async (data) => {
    const { firstname, lastname, username, password } = data;

    try {
      
      const user = {
        fName: firstname, 
        lName: lastname,
        username: username,
        password: password,
        
      };

      // Call the backend endpoint
      const response = await axios.post('http://localhost:8080/auth/register', user);

      // Handle response
      console.log(response.data);
      // After successful registration, redirect or show success message
    } catch (error) {
      console.error("Registration error", error.response?.data || error.message);
      // Handle registration error (e.g., user already exists)
    }
  };

  // Custom validation for password match
  const validatePassword = (value) => {
    return value === watch('password') || "Passwords do not match";
  };

  return (
    <main className="flex justify-center items-center w-[100vw] h-[100vh]">
      <form onSubmit={handleSubmit(onSubmit)} className="bg-white rounded-xl p-2 w-[500px] flex flex-col gap-5">
        <div className="formGroup flex flex-col">
          <label htmlFor="firstname" className="text-dark">First Name:</label>
          <input {...register("firstname", { required: "First name is required" })} type="text" id="firstname" className="rounded-xl bg-light p-2"/>
          {errors.firstname && <span>{errors.firstname.message}</span>}
        </div>
        <div className="formGroup flex flex-col">
          <label htmlFor="lastname" className="text-dark">Last Name:</label>
          <input {...register("lastname", { required: "Last name is required" })} type="text" id="lastname" className="rounded-xl bg-light p-2"/>
          {errors.lastname && <span>{errors.lastname.message}</span>}
        </div>
        <div className="formGroup flex flex-col">
          <label htmlFor="username" className="text-dark">Username:</label>
          <input {...register("username", { required: "Username is required", minLength: { value: 3, message: "Username must be at least 3 characters" } })} type="text" id="username" className="rounded-xl bg-light p-2"/>
          {errors.username && <span>{errors.username.message}</span>}
        </div>
        <div className="formGroup flex flex-col">
          <label htmlFor="password" className="text-dark">Password:</label>
          <input {...register("password", { required: "Password is required", minLength: { value: 8, message: "Password must be at least 8 characters" } })} type="password" id="password" className="rounded-xl bg-light p-2"/>
          {errors.password && <span>{errors.password.message}</span>}
        </div>
        <div className="formGroup flex flex-col">
          <label htmlFor="confirmPassword" className="text-dark">Confirm Password:</label>
          <input {...register("confirmPassword", { validate: validatePassword })} type="password" id="confirmPassword" className="rounded-xl bg-light p-2"/>
          {errors.confirmPassword && <span>{errors.confirmPassword.message}</span>}
        </div>
        <button className="p-2 bg-dark text-white font-bold rounded-xl" type="submit">Register</button>
        <Link href="/login" className="underline text-dark text-sm text-center">Already have an account? Login here!</Link>
      </form>
    </main>
  );
}
