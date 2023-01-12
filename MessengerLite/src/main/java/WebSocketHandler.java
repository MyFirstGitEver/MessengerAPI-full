import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler
{

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception 
	{
		System.out.print(message.getPayload());
	}
}