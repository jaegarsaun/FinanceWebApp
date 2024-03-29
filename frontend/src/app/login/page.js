'use client'
import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { useAuth } from "../components/AuthContext"; // Adjust the import path as needed

export default function page() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useAuth();
  const { register, handleSubmit, formState: { errors } } = useForm();

  const onSubmit = async data => {
    try {
      await login(data.username, data.password);
      // Redirect to another page or update UI upon successful login
      // For example, to redirect in Next.js: router.push('/dashboard');
    } catch (error) {
      console.error("Login error", error);
      // Show login error (e.g., incorrect credentials)
    }
  };

  return (
    <main>
      <form onSubmit={handleSubmit(onSubmit)}>
        {/* Register input fields with react-hook-form */}
        <input {...register("username", { required: true })} placeholder="Username" />
        {errors.username && <p>Username is required</p>}

        <input {...register("password", { required: true })} type="password" placeholder="Password" />
        {errors.password && <p>Password is required</p>}

        <button type="submit">Login</button>
      </form>
    </main>
  );
}

