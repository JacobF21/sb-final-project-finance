function populateStockOptions() {
  var searchInput = document.getElementById('searchInput');
  var searchResults = document.getElementById('searchResults');

  // Fetch data from the server
  fetch('/stock_list')
    .then(response => response.json())
    .then(data => {
      // Listen for input changes
      searchInput.addEventListener('input', function() {
        var searchTerm = searchInput.value.toLowerCase().trim();
        // Clear previous results
        searchResults.innerHTML = '';
        
        // Filter and display matching items
        data.forEach(stock => {
          if (stock.symbol.toLowerCase().concat(stock.shortName.toLowerCase()).includes(searchTerm)) {
            var option = document.createElement('div');
            option.textContent = stock.symbol + "  " +stock.shortName;
            option.addEventListener('click', function() {
              // Set searchInput value when result is clicked
              searchInput.value = stock.symbol;
              // Clear search results after selecting
              searchResults.innerHTML = '';
            });
            searchResults.appendChild(option);
          }
        });
      });
    })
    .catch(error => {
      console.error('Error fetching stock list:', error);
    });
}

// Call the function to initialize search functionality
document.addEventListener('DOMContentLoaded', populateStockOptions);
