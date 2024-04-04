"use client";

import { AiFillHome } from "react-icons/ai";
import { FaShoppingCart, FaMoneyBill } from "react-icons/fa";
import { IoLogOut } from "react-icons/io5";
import Cookies from "js-cookie";
import React from "react";
import { useRouter } from "next/navigation";
import { useState } from "react";
import Link from "next/link";
import ExpenseForm from "./ExpenseForm";
const ICONS = {
  AiFillHome: AiFillHome,
  FaShoppingCart: FaShoppingCart,
  FaMoneyBill: FaMoneyBill,
};

export default function Navbar({ name }) {
  const router = useRouter();
  const NavButton = [
    // { text: "Dashboard", icon: "AiFillHome" },
    { text: "Add Transaction", icon: "FaShoppingCart" },
    { text: "Add Expense", icon: "FaMoneyBill" },
  ];

  const [showExpense, setShowExpense] = useState(false);
  const [showTransaction, setShowTransaction] = useState(false);

  function logout() {
    Cookies.remove("currentUser"); // Remove the cookie or session storage
    Cookies.remove("userId");
    router.push("/login");
  }

  function ExpenseModal() {
    return (
      <div className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center z-50">
        <div className="p-8 border w-96 shadow-lg rounded-md bg-white">
          <div className="text-center">
            <h3 className="text-2xl font-bold text-gray-900">Add Monthly Expense</h3>
            <div className="mt-2 px-7 py-3">
              <ExpenseForm/>
            </div>
            <div className="flex justify-center mt-4">
              {/* Close the modal */}
              <button
                onClick={() => setShowExpense(false)}
                className="px-4 py-2 bg-blue-500 text-white text-base font-medium rounded-md shadow-sm hover:bg-gray-400 focus:outline-none focus:ring-2 focus:ring-gray-300"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }

  function TransactionModal() {
    return (
      <div className="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center z-50">
        <div className="p-8 border w-96 shadow-lg rounded-md bg-white">
          <div className="text-center">
            <h3 className="text-2xl font-bold text-gray-900">Add Transaction</h3>
            <div className="mt-2 px-7 py-3">
              <p className="text-lg text-gray-500">Modal Body</p>
            </div>
            <div className="flex justify-center mt-4">
              {/* Close the modal */}
              <button
                onClick={() => setShowTransaction(false)}
                className="px-4 py-2 bg-blue-500 text-white text-base font-medium rounded-md shadow-sm hover:bg-gray-400 focus:outline-none focus:ring-2 focus:ring-gray-300"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }

  const linkStyle =
    "navButton w-full flex flex-row gap-2 items-center font-medium text-washed p-2 rounded-xl transition-all hover:bg-white hover:text-dark cursor-pointer";

  return (
    <nav className="bg-dark flex flex-col items-center h-[100vh] p-5 gap-3 w-[20vw]">
      <h1 className="text-white font-bold text-3xl">Hello, {name}</h1>
      <div className="wrapper flex flex-col p-2 justify-between h-full w-full">
        <ul className="flex flex-col gap-4 w-full h-full">
          <div onClick={() => setShowExpense(true)} className={linkStyle}>
            <FaMoneyBill />
            Add Expense
          </div>
          {showExpense && <ExpenseModal />}
          <div onClick={() => setShowTransaction(true)} className={linkStyle}>
            <FaShoppingCart />
            Add Transaction
          </div>
          {showTransaction && <TransactionModal />}
        </ul>
        <button
          onClick={logout}
          className="navButton w-full flex flex-row gap-2 items-center font-medium text-washed p-2 rounded-xl transition-all hover:bg-white hover:text-dark"
        >
          <IoLogOut />
          Logout
        </button>
      </div>
    </nav>
  );
}

/* {NavButton.map((item, index) => (
            <Link
              className="navButton w-full flex flex-row gap-2 items-center font-medium text-washed p-2 rounded-xl transition-all hover:bg-white hover:text-dark"
              href=""
            >
              {React.createElement(ICONS[item.icon])}
              {item.text}
            </Link>
          ))} */
