let chart;

// Initialize the Highcharts chart
function initChart() {
    chart = Highcharts.stockChart('coinChart', {
        chart: {
            backgroundColor: '#000000' // Set chart background color to black
        },
        rangeSelector: {
            selected: 1
        },
        title: {
            text: 'BTCUSDT Perpetual Continuous Kline (1 Minute)',
            style: {
                color: '#f0b90b' // Title text color
            }
        },
        plotOptions: {
            candlestick: {
                color: '#ff0000', // Green color for up candles
                upColor: '#00ff00', // Red color for down candles
                lineColor: '#ff0000', // Green border color for up candles
                upLineColor: '#00ff00', // Red border color for down candles
                borderWidth: 1 // Border width for candlesticks
            }
        },
        yAxis: [{
            labels: {
                align: 'right',
                x: -3,
                style: {
                    color: '#ffffff' // Y-axis labels text color
                }
            },
            title: {
                text: 'OHLC',
                style: {
                    color: '#ffffff' // Y-axis title text color
                }
            },
            height: '60%',
            lineWidth: 2,
            resize: {
                enabled: true
            }
        }, {
            labels: {
                align: 'right',
                x: -3,
                style: {
                    color: '#ffffff' // Y-axis labels text color
                }
            },
            title: {
                text: 'Volume',
                style: {
                    color: '#ffffff' // Y-axis title text color
                }
            },
            top: '65%',
            height: '35%',
            offset: 0,
            lineWidth: 2
        }],
        series: [{
            type: 'candlestick',
            name: 'BTCUSDT Perpetual',
            data: []
        }, {
            type: 'column',
            name: 'Volume',
            data: [],
            yAxis: 1,
            color: '#4d94ff' // Color for volume columns
        }]
    });
}

// Function to connect to WebSocket and receive data
function connectWebSocket(symbol) {
    const socket = new WebSocket(`wss://fstream.binance.com/ws/${symbol}@continuousKline_1m`);

    socket.onopen = function() {
        console.log(`WebSocket connected to ${symbol} continuous kline data`);
    };

    socket.onerror = function(error) {
        console.error('WebSocket error:', error);
    };

    socket.onmessage = function(event) {
        const data = JSON.parse(event.data);
        const kline = data.k;

        if (kline) {
            const time = kline.t;
            const open = parseFloat(kline.o);
            const high = parseFloat(kline.h);
            const low = parseFloat(kline.l);
            const close = parseFloat(kline.c);
            const volume = parseFloat(kline.v);

            const candlestick = [time, open, high, low, close];
            const volumePoint = [time, volume];

            if (chart.series.length > 0) {
                const series = chart.series[0]; // Candlestick series
                const volumeSeries = chart.series[1]; // Volume series
                const lastPoint = series.data[series.data.length - 1];

                if (lastPoint && lastPoint.x === time) {
                    // Update the last candlestick and volume
                    lastPoint.update(candlestick);
                    volumeSeries.data[volumeSeries.data.length - 1].update(volumePoint);
                } else {
                    // Add a new candlestick and volume
                    series.addPoint(candlestick);
                    volumeSeries.addPoint(volumePoint);
                }
            }
        }
    };

    return socket; // Return the socket instance
}

// Initialize the chart and connect to WebSocket on page load
document.addEventListener('DOMContentLoaded', function() {
    initChart();
    const socket = connectWebSocket('btcusdt_perpetual');
});


