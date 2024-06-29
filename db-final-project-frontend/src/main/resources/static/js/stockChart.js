// stockChart.js
function initializeStockChart() {
  // Call loadStockChart with the initial symbol
  loadStockChart('0388.HK');
}

document.addEventListener('DOMContentLoaded', initializeStockChart);

function loadStockChart(symbol) {
  fetch(`/five-minute?symbol=${symbol}`)
    .then(response => response.json())
    .then(data => {
      const processedData = data.map(item => [new Date(item.regularMarketUnix * 1000).getTime(), item.regularMarketPrice]);
      const movingAveragePeriod = 10; // Adjust the period as needed
      const movingAverageData = calculateMovingAverage(processedData, movingAveragePeriod);
      Highcharts.stockChart('stockChart', {
        chart: {
          backgroundColor: '#1a1a1a', // Dark background for the chart area
          style: {
            fontFamily: 'Arial, sans-serif',
            color: '#ffffff' // Text color
          },
          borderRadius: 5, // Rounded corners for the chart area
          borderWidth: 1,
          borderColor: '#333333' // Border color
        },
        rangeSelector: {
          selected: 1,
          buttons: [{
            type: 'minute',
            count: 15,
            text: '15m'
          }, {
            type: 'hour',
            count: 1,
            text: '1h'
          }, {
            type: 'day',
            count: 1,
            text: '1d'
          }, {
            type: 'all',
            text: 'All'
          }]
        },
        title: {
          text: '5-Minute Stock Price',
          style: {
            color: '#ffffff' // Title text color
          }
        },
        time: {
          timezone: 'Asia/Hong_Kong'
        },
        xAxis: {
          type: 'datetime',
          labels: {
            style: {
              color: '#ffffff' // X-axis labels text color
            },
            formatter: function () {
              return Highcharts.dateFormat('%H:%M:%S', this.value);
            }
          },
          lineColor: '#707073', // X-axis line color
          minorGridLineColor: '#505053', // X-axis minor grid line color
          tickColor: '#707073' // X-axis tick color
        },
        yAxis: {
          labels: {
            style: {
              color: '#ffffff' // Y-axis labels text color
            }
          },
          gridLineColor: '#707073', // Y-axis grid line color
          title: {
            text: 'Price',
            style: {
              color: '#ffffff' // Y-axis title text color
            }
          }
        },
        series: [{
          name: 'Price',
          data: processedData,
          tooltip: {
            valueDecimals: 2
          }
        }, {
            name: `MA(${movingAveragePeriod})`,
            data: movingAverageData,
            type: 'spline', // Line type for the moving average
            color: '#f0b90b', // Color for the moving average line
            tooltip: {
              valueDecimals: 2
            }
        }],
        tooltip: {
          backgroundColor: 'rgba(0, 0, 0, 0.85)', // Tooltip background color
          borderColor: '#333333', // Tooltip border color
          style: {
            color: '#ffffff' // Tooltip text color
          }
        }
      });
    })
    .catch(error => {
      console.error('Error loading stock chart:', error);
    });
}


function performSearch() {
  var selectedSymbol = document.getElementById('searchInput').value;
  //console.log(selectedSymbol);
  loadStockChart(selectedSymbol);
}

function calculateMovingAverage(data, period) {
  const movingAverageData = [];

  for (let i = period - 1; i < data.length; i++) {
    let sum = 0;
    for (let j = i - period + 1; j <= i; j++) {
      sum += data[j][1]; // Sum up prices over the period
    }
    let average = sum / period;
    movingAverageData.push([data[i][0], average]); // Push [timestamp, moving average] pair
  }

  return movingAverageData;
}
