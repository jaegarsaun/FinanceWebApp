import { ResponsiveContainer, LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip } from 'recharts';

export default function RenderFinanceChart({data}) {
    console.log(data)
    
    const stuff = [{name: 'Date 1', balance: data.balance, savings: data.savings, income: data.income, expenses: data.expenses}]
    console.log(stuff)
    // const data = [{ name: 'Page A', uv: 400, pv: 342, amt: 2400 }, { name: 'Page B', uv: 200, pv: 562, amt: 2400 }, { name: 'Page C', uv: 145, pv: 2400, amt: 2400 }];
    return (
        <ResponsiveContainer width="100%" height="50%">
            <LineChart
                data={stuff}
                margin={{ top: 5, right: 20, bottom: 5, left: 0 }}
            >
                <Line type="monotone" dataKey="balance" stroke="#8884d8" strokeWidth={3}/>
                <Line type="monotone" dataKey="savings" stroke="#8884d8" strokeWidth={3}/>
                <Line type="monotone" dataKey="income" stroke="#8884d8" strokeWidth={3}/>
                <Line type="monotone" dataKey="expenses" stroke="#8884d8" strokeWidth={3}/>
                <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
            </LineChart>
        </ResponsiveContainer>
    );
}
