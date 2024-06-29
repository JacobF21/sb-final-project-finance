var stompClient = null;
var userName = null;
var sessionId = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility
      = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';

    document.getElementById('from').disabled = connected;
}

function connect() {
    var socket = new SockJS('/chatroom');
    stompClient = Stomp.over(socket);

    userName = document.getElementById('from').value;
    stompClient.connect({user:userName}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        // 廣播
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });

        // 私人
        stompClient.subscribe('/user/subscribe', function(messageOutput) {
        showMessageOutput(JSON.parse(messageOutput.body));
        });

    });

}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    userName = null;
    console.log("Disconnected");
}

function sendMessage() {
    var from = document.getElementById('from').value;
    var text = document.getElementById('text').value;
    if(text != ''){
        stompClient.send("/app/chat", {}, JSON.stringify({'from':from, 'text':text}));
        document.getElementById('text').value = '';
    }
}

function showMessageOutput(messageOutput) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.classList.add('message');
  
    var fromSpan = document.createElement('span');
    fromSpan.classList.add('message-content', 'from-me'); // Adding 'message-content' class for consistent styling
  
    // Customize the message format here
    var messageText = messageOutput.message.from + " (" + messageOutput.dateStr + "): " + messageOutput.message.text;
    fromSpan.textContent = messageText;
  
    p.appendChild(fromSpan);
  
    response.appendChild(p);
  
    var elem = document.getElementById('scroll');
    elem.scrollTop = elem.scrollHeight;
  }

function toggleChat() {
    var chatContainer = document.querySelector('.chat-container');
    var stockChart = document.getElementById('stockChart');

    chatContainer.classList.toggle('hidden'); // Toggle the 'hidden' class
    stockChart.classList.toggle('chart-expanded'); // Toggle the 'chart-expanded' class
  }
