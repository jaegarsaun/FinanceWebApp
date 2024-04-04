import axios from "axios";
import { useState } from "react";
import Cookies from "js-cookie"; // Import Cookies

export default function IncomeForm() {
  const [income, setIncome] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userId = Cookies.get("userId"); // Fetch userId from cookies
    const accountId = Cookies.get("accountId");

    try {
      
      const updateResponse = await axios.put(`http://localhost:8080/api/accounts/update/${accountId}`, {
        income: income, 
      });
      
      console.log("Income updated:", updateResponse.data);
    } catch (error) {
      console.error("Error updating income:", error);

    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-2">
      <label className="flex flex-col text-start">
        Your Income
        <input
          type="number"
          className="p-2 rounded-xl bg-light"
          value={income}
          onChange={(e) => setIncome(e.target.value)}
          required
        />
      </label>
      <button
        type="submit"
        className="w-full p-2 bg-dark text-white font-medium rounded-xl"
      >
        Submit
      </button>
    </form>
  );
}

