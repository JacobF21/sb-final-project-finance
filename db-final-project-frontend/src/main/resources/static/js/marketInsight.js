document.addEventListener('DOMContentLoaded', function () {
  fetchAndRenderChartTopTen();
  fetchAndRenderTreemap();
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

  function fetchAndRenderTreemap() {
    const apiUrl = "/latestPercentChange";
  
    fetch(apiUrl)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        renderTreemap(data);
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
  

  function renderTreemap(data) {
    // Parse and format the data for Highcharts treemap
    const seriesData = data.map(item => {
        // Calculate brightness for green based on the value (0 to 1)
        const brightness = Math.abs(item.regularMarketChangePercent) / 8; // Adjusted for range and scale
        let color;

        if (item.regularMarketChangePercent > 0) {
            color = Highcharts.color('#00FF00').brighten(-brightness).get(); // Brighten towards green for positive values
        } else if (item.regularMarketChangePercent < 0) {
            color = Highcharts.color('#FF0000').brighten(brightness).get(); // Brighten towards red for negative values
        } else {
            color = '#FFFFFF'; // White for values close to 0
        }
        
        return {
            name: item.symbol,
            color: color,
            value: Math.abs(item.regularMarketChangePercent) // Absolute value for size
        };
    });

    // Initialize Highcharts treemap chart
    Highcharts.chart('treemapContainer', {
        chart: {
            type: 'treemap',
            height: '100%',
            backgroundColor: 'black'
        },
        title: {
            text: 'Regular Market Change Percent',
            style: {
                color: '#FFFFFF'
            }
        },
        series: [{
            type: 'treemap',
            layoutAlgorithm: 'squarified',
            data: seriesData,
            // Using color for individual item color
            keys: ['name', 'value', 'color'],
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        }],
        colorAxis: {
            min: -10, // Minimum value for color axis
            max: 10, // Maximum value for color axis
            stops: [
                [0, '#FF0000'], // Red for negative values
                [0.5, '#FFFFFF'], // White for values close to 0
                [1, '#00FF00'] // Green for positive values
            ],
            labels: {
                format: '{value}%',
                style: {
                    color: '#FFFFFF'
                }
            }
        }
    });
}

// Example usage with fetching data from API
fetch('https://your-api-endpoint.com/data')
    .then(response => response.json())
    .then(data => {
        // Call renderTreemap function with fetched data
        renderTreemap(data);
    })
    .catch(error => {
        console.error('Error fetching data:', error);
        // Handle error loading data from API
    });
