document.addEventListener('DOMContentLoaded', function () {
  fetchAndRenderChartTopTen();
  fetchAndRenderChartPriceChange();
});

function fetchAndRenderChartTopTen() {
    const apiUrl = "/top_10_mktcap"; // Replace with your API endpoint URL
  
    fetch(apiUrl)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        renderChartTopTen(data); // Function to render the chart with fetched data
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }

  function fetchAndRenderChartPriceChange() {
    const apiUrl = "/latestPercentChange"; // Replace with your API endpoint URL
  
    fetch(apiUrl)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        renderChartPriceChange(data); // Function to render the chart with fetched data
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }
  

  function renderChartTopTen(data) {
    // Convert marketCap to billions and format to 2 decimal places
    const seriesData = data.map(item => ({
      name: item.shortName,
      symbol: item.symbol,
      value: parseFloat((item.marketCap / 1000000000).toFixed(2))
    }));
  
    // Define custom colors for bubbles
    const colors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'];
  
    // Initialize Highcharts packed bubble chart
    Highcharts.chart('top10marketcap', {
      chart: {
        type: 'packedbubble',
        height: '100%',
        backgroundColor: 'black' // Set background color to black
      },
      title: {
        text: 'Top 10 HK Stock Market Cap',
        style: {
          color: '#FFFFFF' // Title text color
        }
      },
      plotOptions: {
        packedbubble: {
          dataLabels: {
            enabled: true,
            format: '{point.name}',
            style: {
              color: 'white', // Data labels color
              textOutline: 'none' // No text outline for data labels
            }
          },
          tooltip: {
            pointFormat: '<b>{point.name} ({point.symbol}):</b> {point.value}B',
            backgroundColor: 'rgba(0, 0, 0, 0.85)' // Tooltip background color
          },
          minPointSize: 200, // Minimum size of the bubble
          maxSize: '80%', // Maximum size of the bubble relative to the plot area
          colorByPoint: true, // Color each bubble individually
          layoutAlgorithm: {
            gravitationalConstant: 0.0001, // Lower value increases the space between bubbles
            splitSeries: false,
            seriesInteraction: false,
            dragBetweenSeries: false,
            parentNodeLimit: true
          }
        }
      },
      series: [{
        name: 'Market Cap',
        data: seriesData,
        color: 'transparent', // Set series color to transparent to avoid coloring the border
        marker: {
          fillColor: {
            radialGradient: { cx: 0.4, cy: 0.3, r: 0.7 },
            stops: [
              [0, 'rgba(255,255,255,0.5)'],
              [1, Highcharts.color(colors[0]).setOpacity(0.5).get('rgba')]
            ]
          }
        }
      }]
    });
  }
  