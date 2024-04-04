import React, { useState } from "react";
import axios from 'axios';
import Cookies from "js-cookie";

const ExpenseForm = () => {
  const [expenseType, setExpenseType] = useState("");
  const [accountFrom, setAccountFrom] = useState("");
  const [accountTo, setAccountTo] = useState("");
  const [accountField, setAccountField] = useState("");
  const [executionDate, setExecutionDate] = useState("");
  const [amount, setAmount] = useState("");

  const accountId = Cookies.get('accountId'); 
  const userId = Cookies.get('userId');

  const handleExpenseTypeChange = (e) => {
    setExpenseType(e.target.value);
    // Reset Account To when Expense Type changes
    setAccountTo('');
  };
  const handleAccountFromChange = (e) => {
    setAccountFrom(e.target.value);
  };
  const handleAccountToChange = (e) => {
    setAccountTo(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/expenses/create', {
        accountId, 
        userId,
        expenseType,
        accountFrom,
        accountTo: expenseType === 'Transfer' ? accountTo : null,
        accountField,
        executionDate,
        amount
      });
      console.log('Expense created:', response.data);
      // Reset form fields after successful submission
      setExpenseType("");
      setAccountFrom("");
      setAccountTo("");
      setAccountField("");
      setExecutionDate("");
      setAmount("");
    } catch (error) {
      console.error('Error creating expense:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-2">
      {/* Expense Type */}
      <label className="flex flex-col text-start">
        Expense Type:
        <select
          className="p-2 rounded-xl bg-light"
          value={expenseType}
          onChange={handleExpenseTypeChange}
          required
        >
          <option value="">Select an option</option>
          <option value="Withdrawal">Withdrawal</option>
          <option value="Deposit">Deposit</option>
          <option value="Transfer">Transfer</option>
        </select>
      </label>
      {/* Account From */}
      <label className="flex flex-col text-start">
        Account From:
        <select
          className="p-2 rounded-xl bg-light"
          value={accountFrom}
          onChange={handleAccountFromChange}
          required
        >
          <option value="">Select an option</option>
          <option value="balance">Balance</option>
          <option value="savings">Savings</option>
        </select>
      </label>
      {/* Account To */}
      {expenseType === 'Transfer' && (
        <label className="flex flex-col text-start">
          Account To:
          <select
            className="p-2 rounded-xl bg-light"
            value={accountTo}
            onChange={handleAccountToChange}
            required
          >
            <option value="">Select an option</option>
            <option value="balance">Balance</option>
            <option value="savings">Savings</option>
          </select>
        </label>
      )}
      {/* Execution Date */}
      <label className="flex flex-col text-start">
        Execution Date:
        <input
          type="date"
          className="p-2 rounded-xl bg-light"
          value={executionDate}
          onChange={(e) => setExecutionDate(e.target.value)}
          required
        />
      </label>
      {/* Amount */}
      <label className="flex flex-col text-start">
        Amount:
        <input
          type="number"
          className="p-2 rounded-xl bg-light"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          required
        />
      </label>
      {/* Submit Button */}
      <button type="submit" className="w-full p-2 bg-dark text-white font-medium rounded-xl">Submit</button>
    </form>
  );
};

export default ExpenseForm;
