import { FaSackDollar } from "react-icons/fa6";
import { AiFillDollarCircle } from "react-icons/ai";
import { FaMoneyCheckDollar } from "react-icons/fa6";
import { FaHandHoldingDollar } from "react-icons/fa6";
import Navbar from "../components/Navbar";
import React from "react"
const ICONS = {
  AiFillDollarCircle: AiFillDollarCircle,
  FaSackDollar: FaSackDollar,
  FaMoneyCheckDollar: FaMoneyCheckDollar,
  FaHandHoldingDollar: FaHandHoldingDollar,
};
export default function Home() {
  let userBalanceAmount = 1000,
    userExpenseAmount = 1000,
    userSavingsAmount = 1000,
    userIncomeAmount = 1000;

  const moneyCards = [
    { name: "Balance", iconName: "AiFillDollarCircle", amount: userBalanceAmount },
    { name: "Expenses", iconName: "FaMoneyCheckDollar", amount: userExpenseAmount },
    { name: "Income", iconName: "FaHandHoldingDollar", amount: userIncomeAmount },
    { name: "Savings", iconName: "FaSackDollar", amount: userSavingsAmount },
  ];

  return (
    <main className="flex">
      <Navbar/>
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
