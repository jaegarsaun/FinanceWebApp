import { ResponsiveContainer, LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip } from 'recharts';

export default function RenderFinanceChart({data}) {
    console.log(data)

    return (
        <ResponsiveContainer width="100%" height="50%">
            <LineChart
                data={data}
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
