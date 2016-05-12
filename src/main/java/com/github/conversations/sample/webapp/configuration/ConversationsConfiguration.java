package com.github.conversations.sample.webapp.configuration;

import com.github.conversations.sample.webapp.users.SampleUserRepository;
import com.github.conversations.sample.webapp.users.SampleUserService;
import com.github.maximilientyc.conversations.domain.*;
import com.github.maximilientyc.conversations.repositories.mongodb.MongoDbConversationRepository;
import com.github.maximilientyc.conversations.repositories.mongodb.MongoDbMessageRepository;
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

	private final MongoDatabase mongoDatabase;
	private final ConversationRepository conversationRepository;
	private final ParticipantFactory participantFactory;
	private final UserService userService;
	private final ConversationService conversationService;
	private final ConversationFactory conversationFactory;
	private final MessageRepository messageRepository;

	public ConversationsConfiguration() {
		MongoClient mongo = new MongoClient("localhost");
		this.mongoDatabase = mongo.getDatabase("conversations");
		this.conversationRepository = new MongoDbConversationRepository(mongoDatabase);
		this.participantFactory = new ParticipantFactory(new SampleUserRepository());
		this.userService = new SampleUserService();
		this.messageRepository = new MongoDbMessageRepository(mongoDatabase);
		this.conversationService = new ConversationService(conversationRepository, messageRepository);
		this.conversationFactory = new ConversationFactory(conversationService);
	}

	@Bean
	public ConversationController conversationController() throws UnknownHostException {
		return new ConversationController(
				participantFactory,
				conversationRepository,
				conversationFactory,
				userService);
	}

}
