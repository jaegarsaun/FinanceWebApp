'use client'

import { AiFillHome } from "react-icons/ai";
import { FaShoppingCart } from "react-icons/fa";
import { IoLogOut } from "react-icons/io5";
import Cookies from "js-cookie";
import React from "react";
import { useRouter } from "next/navigation";
const ICONS = {
  AiFillHome: AiFillHome,
  FaShoppingCart: FaShoppingCart,
};

export default function Navbar({name}) {
  const router = useRouter();
  const NavButton = [
    { text: "Dashboard", icon: "AiFillHome" },
    { text: "Transactions", icon: "FaShoppingCart" },
  ];

  function logout() {
    Cookies.remove('currentUser'); // Remove the cookie or session storage
    Cookies.remove('userId');
    router.push('/login')
  }
  
  return (
    <nav className="bg-dark flex flex-col items-center h-[100vh] p-5 gap-3 w-[20vw]">
      <h1 className="text-white font-bold text-3xl">Hello, {name}</h1>
      <div className="wrapper flex flex-col p-2 justify-between h-full w-full">
        <ul className="flex flex-col gap-4 w-full h-full">
          {NavButton.map((item, index) => (
            <button className="navButton w-full flex flex-row justify-between items-center font-medium text-washed p-2 rounded-xl transition-all hover:bg-white hover:text-dark">
              {React.createElement(ICONS[item.icon])}
              {item.text}
            </button>
          ))}
        </ul>
        <button onClick={logout} className="navButton w-full flex flex-row justify-between items-center font-medium text-washed p-2 rounded-xl transition-all hover:bg-white hover:text-dark">
          <IoLogOut />
          Logout
        </button>
      </div>
    </nav>
  );
}
