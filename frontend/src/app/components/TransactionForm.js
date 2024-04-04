import { useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";

export default function TransactionForm() {
  // States for form fields
  const [transactionTypeId, setTransactionTypeId] = useState("");
  const [accountTo, setAccountTo] = useState("");
  const [cost, setCost] = useState("");
  const [transactionTypes, setTransactionTypes] = useState([]);
  const [accountFrom, setAccountFrom] = useState("");

  // Getting user ID from cookies
  const userId = Cookies.get("userId");

  const handleAccountFromChange = (e) => {
    setAccountFrom(e.target.value);
  };
  const handleAccountToChange = (e) => {
    setAccountTo(e.target.value);
  };

  useEffect(() => {
    async function getTransactionTypes() {
      try {
        const { data } = await axios.get(
          "http://localhost:8080/api/transaction-types"
        );
        setTransactionTypes(data);
      } catch (error) {
        console.error("Failed to fetch transaction types:", error);
      }
    }
    getTransactionTypes();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const transactionData = {
      transactionTypeId,
      cost: cost, // Ensuring cost is sent as a Float
      userID: userId, // Ensuring userId is sent as an Integer
      accountFrom,
      accountTo: transactionTypeId == 3 ? accountTo : null,
      // postedAt will be handled in the backend
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/transactions/create",
        transactionData
      );
      console.log("Transaction created:", response.data);
    } catch (error) {
      console.error("Error creating transaction:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-2">
      <label className="flex flex-col text-start">
        Transaction Type
        <select
          value={transactionTypeId}
          onChange={(e) => setTransactionTypeId(e.target.value)}
          className="p-2 rounded-xl bg-light"
          required
        >
          <option value="">Select a Transaction Type</option>
          {transactionTypes.map((transactionType) => (
            <option
              key={transactionType.transactionTypeId}
              value={transactionType.transactionTypeId}
            >
              {transactionType.name.charAt(0).toUpperCase() +
                transactionType.name.slice(1)}
            </option>
          ))}
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
      {transactionTypeId == 3 && (
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
      <label className="flex flex-col text-start">
        Cost
        <input
          type="number"
          value={cost}
          onChange={(e) => setCost(e.target.value)}
          className="p-2 rounded-xl bg-light"
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
