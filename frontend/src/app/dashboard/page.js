'use client'
import { FaSackDollar } from "react-icons/fa6";
import { AiFillDollarCircle } from "react-icons/ai";
import { FaMoneyCheckDollar } from "react-icons/fa6";
import { FaHandHoldingDollar } from "react-icons/fa6";
import axios from 'axios';
import { useEffect, useState } from "react";
import { useRouter } from 'next/navigation';
import Navbar from "../components/Navbar";
import React from "react"
import Cookies from "js-cookie";
const ICONS = {
  AiFillDollarCircle: AiFillDollarCircle,
  FaSackDollar: FaSackDollar,
  FaMoneyCheckDollar: FaMoneyCheckDollar,
  FaHandHoldingDollar: FaHandHoldingDollar,
};
export default function Home() {
    const [userInfo, setUserInfo] = useState('');
    const [accountInfo, setAccountInfo] = useState('');
    const router = useRouter();
    useEffect(() => {
        const fetchUserInfo = async() => {
            const userId = Cookies.get('userId')
            // if somehow there is no userid then redirect to login page
            if(!userId){ // we shouldnt ever get here because of middleware but just in case
                router.push('/login');
                return;
            }
            // get user info from backend
            try{
                const response = await axios.get(`http://localhost:8080/api/users/${userId}`)
                setUserInfo(response.data)
                
            }catch(error){
                console.log('Failed to fetch user info', error)
                // TODO: Do something else with this like display an error the the user
            }
        }
        fetchUserInfo();

        const fetchAccountInfo = async() => {
            const userId = Cookies.get('userId');

                        
            try{
                const response = await axios.get(`http://localhost:8080/api/accounts/user/${userId}`)
                setAccountInfo(response.data)

            }catch(error){
                console.log('Error getting account info', error);
            }
        }
        fetchAccountInfo()
        
    }, [router]);
    console.log(userInfo);
    console.log(accountInfo)
  
  const moneyCards = [
    { name: "Balance", iconName: "AiFillDollarCircle", amount: accountInfo.balance },
    { name: "Expenses", iconName: "FaMoneyCheckDollar", amount: accountInfo.expenses },
    { name: "Income", iconName: "FaHandHoldingDollar", amount: accountInfo.income },
    { name: "Savings", iconName: "FaSackDollar", amount: accountInfo.savings },
  ];

  return (
    <main className="flex">
      <Navbar name={userInfo.username}/>
      <section className="dashboard p-10 flex flex-row w-full justify-between">
        <section className="main flex flex-col w-[65vw]">
          <div className="yourMoney flex flex-col gap-2 md:flex-row md:justify-between md:items-center">
            {moneyCards.map((item, index) => (
              <div key={index} className="moneyCard flex flex-col bg-white justify-evenly rounded-lg p-2 gap-2 w-[150px]">
                {React.createElement(ICONS[item.iconName])}
                <p className="font-bold text-xl">${item.amount}</p>
                <p className="font-light text-sm">{item.name}</p>
              </div>
            ))}
          </div>
        </section>
        <section className="side flex grow">
          hello
        </section>
      </section>
    </main>
  );
}
