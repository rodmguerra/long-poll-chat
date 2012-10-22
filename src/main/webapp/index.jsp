<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chat</title>
    <script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="resources/js/knockout-2.0.0.js"></script>
</head>
<body>
<h1>Chat</h1>
<div id="joinRoomDiv">
    <h2>Insert your name and click join:</h2>
    <input type="text" name="user" data-bind="value: userName"/>
    <input type="button" value="joinRoom" data-bind="click: joinRoom"/>
</div>
<br/>
<div id="chatMessagesDiv">
    <ul data-bind="foreach: chatMessages">
        <li style="list-style: none;">
            <span data-bind="text: $data">A Chat Message</span>
        </li>
    </ul>
</div>
<br/>
<div id="chatInputDiv">
    <textarea data-bind="value: message">
    </textarea>
    <br/>
    <input type="button" data-bind="click: sendChatMessage" value="say"/>
</div>
<br/>
<div id="statusMessagesDiv">
    <ul data-bind="foreach: statusMessages">
        <li style="list-style: none;">
            <span data-bind="text: $data">A Status Message</span>
        </li>
    </ul>
</div>
<script type="text/javascript" src="resources/js/index.js"></script>
</body>
</html>