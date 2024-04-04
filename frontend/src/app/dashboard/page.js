"use client";
import { FaSackDollar } from "react-icons/fa6";
import { AiFillDollarCircle } from "react-icons/ai";
import { FaMoneyCheckDollar } from "react-icons/fa6";
import { FaHandHoldingDollar } from "react-icons/fa6";
import axios from "axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import Navbar from "../components/Navbar";
import React from "react";
import Cookies from "js-cookie";
import { LineChart } from "recharts";
import RenderFinanceChart from "../components/RenderFinanceChart";
const ICONS = {
  AiFillDollarCircle: AiFillDollarCircle,
  FaSackDollar: FaSackDollar,
  FaMoneyCheckDollar: FaMoneyCheckDollar,
  FaHandHoldingDollar: FaHandHoldingDollar,
};
export default function Home() {
  const [userInfo, setUserInfo] = useState("");
  const [accountInfo, setAccountInfo] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [transactionTypes, setTransactionTypes] = useState({});
  const [acountHistoryData, setMappedAccountHistoryData] = useState([]);
  const router = useRouter();
  useEffect(() => {
    const fetchUserInfo = async () => {
      const userId = Cookies.get("userId");
      // if somehow there is no userid then redirect to login page
      if (!userId) {
        // we shouldnt ever get here because of middleware but just in case
        router.push("/login");
        return;
      }
      // get user info from backend
      try {
        const response = await axios.get(
          `http://localhost:8080/api/users/${userId}`
        );
        setUserInfo(response.data);
      } catch (error) {
        console.log("Failed to fetch user info", error);
        setTransactions([]); // Reset to empty array if response is not as expected
        // TODO: Do something else with this like display an error the the user
      }
    };
    fetchUserInfo();

    const fetchAccountInfo = async () => {
      const userId = Cookies.get("userId");

      try {
        const response = await axios.get(
          `http://localhost:8080/api/accounts/user/${userId}`
        );
        console.log(response.data)
        setAccountInfo(response.data);
        // Create a cookie and put accountId in there
        Cookies.set("accountId", response.data.accountId.toString(), { expires: 1 });
      } catch (error) {
        console.log("Error getting account info", error);
      }
    };
    fetchAccountInfo();

    const fetchAccountHistory = async () => {
        const userId = Cookies.get("userId");
        try{
            const response = await axios.get(`http://localhost:8080/api/accounts/history/user/${userId}`);
            const responseData = response.data;

            // Map the response data to the desired format
            const mappedData = responseData.map(data => ({
                name: timestampConvert(data.REVTSTMP),
                balance: data.balance,
                savings: data.savings,
                income: data.income,
                expenses: data.expenses
            }));

            // Set the mapped data to the component state
            setMappedAccountHistoryData(mappedData);
        }catch(error) {
            console.log("Error Getting Account Info");
        }
    }
    fetchAccountHistory();

    const fetchUserTransactions = async () => {
      const userId = Cookies.get("userId");
      try {
        const response = await axios.get(
          `http://localhost:8080/api/transactions/user/${userId}`
        );
        const transactionTypesResponse = await axios.get(
          "http://localhost:8080/api/transaction-types"
        );
        // Ensure the response is always treated as an array
        const transactionsData = Array.isArray(response.data)
          ? response.data
          : [];
        console.log(response.data);
        setTransactions(transactionsData);
        
        // Convert array to object for easy lookup
        const typesLookup = transactionTypesResponse.data.reduce(
          (acc, current) => {
            let color = "";
            switch (current.name.toLowerCase()) {
              case "withdrawal":
                color = "#FFCCCC";
                break; // Light red
              case "deposit":
                color = "#CCFFCC";
                break; // Light green
              case "transfer":
                color = "#CCCCFF";
                break; // Light blue
              case "expense":
                color = "#FFFFCC";
                break; // Light yellow
              default:
                color = "#F3F6FB"; // Default light blue
            }
            acc[current.transactionTypeId] = {
              name: current.name,
              color: color,
            };
            return acc;
          },
          {}
        );

        setTransactionTypes(typesLookup);
      } catch (error) {
        console.log("Error getting transaction info", error);
        setTransactions([]); // Ensure to reset to an empty array on error
      }
    };
    fetchUserTransactions();
  }, [router]);
  console.log(userInfo);
  console.log(accountInfo);

  function timestampConvert(timestamp){
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    const date = new Date(timestamp);
    const month = months[date.getMonth()];
    const day = String(date.getDate()).padStart(2, '0'); // Ensure two digits for day
    return month + ' ' + day;
  }

  const moneyCards = [
    {
      name: "Balance",
      iconName: "AiFillDollarCircle",
      amount: accountInfo.balance,
    },
    {
      name: "Expenses",
      iconName: "FaMoneyCheckDollar",
      amount: accountInfo.expenses,
    },
    {
      name: "Income",
      iconName: "FaHandHoldingDollar",
      amount: accountInfo.income,
    },
    { name: "Savings", iconName: "FaSackDollar", amount: accountInfo.savings },
  ];

  return (
    <main className="flex">
      <Navbar name={userInfo.username} />
      <section className="dashboard p-10 flex flex-row w-full justify-between gap-4">
        <section className="main flex flex-col w-[55vw] gap-5">
          <div className="yourMoney flex flex-col gap-2 md:flex-row md:justify-between md:items-center">
            {moneyCards.map((item, index) => (
              <div
                key={index}
                className="moneyCard flex flex-col bg-white justify-evenly rounded-lg p-2 gap-2 w-1/4"
              >
                {React.createElement(ICONS[item.iconName])}
                <p className="font-bold text-xl">${item.amount}</p>
                <p className="font-light text-sm">{item.name}</p>
              </div>
            ))}
          </div>
          <div className="w-full h-full bg-white rounded-xl p-2 flex flex-col gap-4">
            <div>
                <h1 className="font-bold text-xl">Finances</h1>
            </div>
          <RenderFinanceChart data={acountHistoryData}/>
          </div>
        </section>
        <section className="side flex flex-col grow items-center gap-2 bg-white rounded-xl p-2">
          <h1 className="font-bold text-2xl text-center">
            Transaction History
          </h1>
          {transactions.map((item, index) => (
            <div
              key={index}
              style={{
                backgroundColor:
                  transactionTypes[item.transactionTypeId]?.color,
              }}
              className="w-full rounded-xl p-2 flex flex-row justify-between items-center"
            >
              <div className="font-medium">
              {transactionTypes[item.transactionTypeId]?.name.charAt(0).toUpperCase() + transactionTypes[item.transactionTypeId]?.name.slice(1).toLowerCase()}
              </div>
              <div>${item.cost}</div>
            </div>
          ))}
        </section>
      </section>
    </main>
  );
}
