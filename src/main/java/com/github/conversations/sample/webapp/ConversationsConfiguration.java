package com.github.conversations.sample.webapp;

import com.github.maximilientyc.conversations.domain.*;
import com.github.maximilientyc.conversations.repositories.mongodb.MongoDbConversationRepository;
import com.github.maximilientyc.conversations.restadapter.ConversationController;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * Created by @maximilientyc on 10/05/2016.
 */
@Configuration
public class ConversationsConfiguration {

	private MongoDatabase mongoDatabase;

	public ConversationsConfiguration() {
		MongoClient mongo = new MongoClient("localhost");
		this.mongoDatabase = mongo.getDatabase("conversations");
	}

	@Bean
	public ConversationController conversationController() throws UnknownHostException {
		ConversationRepository conversationRepository = new MongoDbConversationRepository(mongoDatabase);
		ParticipantFactory participantFactory = new ParticipantFactory(new SampleUserRepository());
		ConversationFactory conversationFactory = new ConversationFactory(
				new ConversationService(
						conversationRepository,
						new SampleMessageRepository()));
		UserService userService = new SampleUserService();

		return new ConversationController(
				participantFactory,
				conversationRepository,
				conversationFactory,
				userService);
	}

}
