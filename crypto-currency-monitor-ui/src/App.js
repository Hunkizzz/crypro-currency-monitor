import React, {Component} from 'react';
import { Chart as ChartJS } from 'chart.js/auto'
import { Line }            from 'react-chartjs-2'

class CryptoCurrencyGraph extends Component {
    constructor(props) {
        super(props);
        this.state = {
            cryptoCurrencies: ["BTC", "ETH", "XRP"],
            cryptoCurrencyData: [10000, 200, 1]
        };
    }

    render() {
        const {cryptoCurrencies, cryptoCurrencyData} = this.state;
        const data = {
            labels: cryptoCurrencies,
            datasets: [
                {
                    label: 'Crypto Currency Value',
                    data: cryptoCurrencyData
                }
            ]
        };
        return (
            <div>
                <h2>Crypto Currency Graph</h2>
                <Line data={data}/>
            </div>
        );
    }
}

export default CryptoCurrencyGraph;