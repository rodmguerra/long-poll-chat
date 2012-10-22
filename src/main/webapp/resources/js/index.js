var ChatView = function() {
     var self = this;
     self.userName = ko.observable();
     self.chatMessages = ko.observableArray([]);
     self.statusMessages = ko.observableArray([]);
     self.message = ko.observable();

     self.joinRoom = function(data) {
         $.get("/chat/joinRoom", {userName: self.userName()}, function(data){
             self.statusMessages.push(data);
             self.listenToChatMessages();
             self.listenToStatusMessages();
         });
     }

     self.listenToStatusMessages = function(data) {
         $.get("/chat/listenToStatusMessages", {}, function(data){
             self.statusMessages.push(data);
         }).complete(function(data){
             self.listenToStatusMessages();
         });
     }

     self.listenToChatMessages = function(data) {
         $.get("/chat/listenToChatMessages", {}, function(data){
             self.chatMessages.push(data);
         }).complete(function(data){
             self.listenToChatMessages();
         });
     }

     self.sendChatMessage = function(data) {
         $.get("/chat/sendChatMessage", {message: self.message()}, function(data){
             self.chatMessages.push(data);
         });
     }
 }

 ko.applyBindings(new ChatView());