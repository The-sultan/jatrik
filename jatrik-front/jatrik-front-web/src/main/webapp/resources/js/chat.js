var chatbox = {
	openChats : {},
	config : {
			width : 200,
			gap : 20
		},
	getNextOffset : function(){
			return (this.config.width + this.config.gap) * Object.keys(self.openChats).length;
	},
	keepAlive : function(){
		var sendingMsg = {
			message : "",
			sender : currentUser.nick,
			receiver : ""
		}
		self.websocket.send(JSON.stringify(sendingMsg));
		setTimeout(self.keepAlive, 10000);
	},
	init : function(){
		this.websocket = new WebSocket('ws://' + document.location.host + '/jatrik-front-web/chat'); 
		self = this;
		
		this.websocket.onmessage = this.onMessageReceived;
		this.websocket.onclose = this.onSocketClose;
		setTimeout(self.keepAlive, 10000);
		$("#chat-autocomplete").autocomplete({
			source: usuarios,
			select: function(evt,ui){
				var receiver = ui.item.value;
				var id = ui.item.value + "-chat-window";
				if(!self.openChats[receiver]){
					var el = document.createElement('div');
					el.setAttribute('id', id);
					self.openChats[receiver] = $(el).chatbox(
					{	
						id:id, 
						user:{nick : ui.item.value},
						title : ui.item.value,
						width : self.config.width,
						offset : self.getNextOffset(),
						messageSent : function(id, user, msg) {
							//$("#log").append(id + " said: " + msg + "<br/>");
							var sendingMsg = {
								message : msg,
								sender : currentUser.nick,
								receiver : user.nick
							}
							self.websocket.send(JSON.stringify(sendingMsg ));
							$("#"+id).chatbox("option", "boxManager").addMsg(currentUser.nick, msg);
						}
					});
				}else{
					self.openChats[receiver].chatbox("option", "boxManager").highlightBox();
				}
			}
		});
	},

	onMessageReceived : function(evt){
		if(!evt.data){
			console.log("pong");
			return;
		}
		var msg = JSON.parse(evt.data); // native API
		var id = msg.sender + "-chat-window";
		if(!self.openChats[msg.sender]){
			var el = document.createElement('div');
			el.setAttribute('id', id);
			self.openChats[msg.sender] = $(el).chatbox(
			{	
				id:id, 
				user:{nick : msg.sender},
				title : msg.sender,
				width : self.config.width,
				offset : self.getNextOffset(),
				messageSent : function(id, user, msg) {
					//$("#log").append(id + " said: " + msg + "<br/>");
					var sendingMsg = {
						message : msg,
						sender : currentUser.nick,
						receiver : user.nick
					}
					self.websocket.send(JSON.stringify(sendingMsg ));
					$("#"+id).chatbox("option", "boxManager").addMsg(currentUser.nick, msg);
				}
			});
		}
		self.openChats[msg.sender].chatbox("option","boxManager").addMsg(msg.sender,msg.message);
		console.log(msg.message);
	},
	sendMessage : function(msg){
		this.websocket.send(msg);
	},
	
	onSocketClose : function(){
		this.websocket = new WebSocket('ws://' + document.location.host + '/jatrik-front-web/chat'); 
		this.websocket.onmessage = this.onMessageReceived;
		this.websocket.onclose = this.onSocketClose;
	}
	
}


$(document).ready(function() {
	chatbox.init();
});
