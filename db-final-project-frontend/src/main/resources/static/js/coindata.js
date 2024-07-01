// Define WebSocket endpoint (Binance WebSocket API)
const socket = new WebSocket('wss://stream.binance.com:9443/ws');

// Function to subscribe to a symbol's stream
function subscribeToSymbol(symbol) {
  // Construct the subscription message
  const subscriptionMsg = JSON.stringify({
    method: 'SUBSCRIBE',
    params: [`${symbol.toLowerCase()}@kline_1m`], // Adjust the stream type as needed (e.g., kline_1m, trades, etc.)
    id: 1
  });

  // Send subscription message to WebSocket
  socket.send(subscriptionMsg);
}

// Event listener for WebSocket open connection
socket.onopen = function(event) {
  console.log('WebSocket connected to Binance');
  
  // Subscribe to a symbol's stream once connected (example: BTCUSDT)
  subscribeToSymbol('BTCUSDT');
};

// Event listener for incoming messages from WebSocket
socket.onmessage = function(event) {
  const eventData = JSON.parse(event.data);
  console.log('Received message:', eventData);

  // Handle the incoming data (e.g., update Highcharts)
  updateChart(eventData);
};

// Event listener for WebSocket errors
socket.onerror = function(error) {
  console.error('WebSocket error:', error);
};

// Event listener for WebSocket close
socket.onclose = function(event) {
  console.log('WebSocket closed:', event);
};

// Function to update Highcharts with new data
function updateChart(data) {
  // Implement your Highcharts update logic here
  // Example: update series data with new price data
}
