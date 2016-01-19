package org.rubik.sandbox.reactor.web;

import org.rubik.sandbox.reactor.domain.Client;
import org.rubik.sandbox.reactor.domain.TradeServer;
import org.rubik.sandbox.reactor.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.bus.Event;
import reactor.bus.EventBus;

//@RestController
public class TradeController {

	@Autowired
	private ClientRepository clients;

	@Autowired
	private EventBus         eventBus;

	@Autowired
	private TradeServer      tradeServer;

//	@Autowired
//	public TradeController(ClientRepository clients,
//	                       EventBus eventBus,
//	                       TradeServer tradeServer) {
//		this.clients = clients;
//		this.eventBus = eventBus;
//		this.tradeServer = tradeServer;
//	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Client> listClients() {
		return clients.findAll();
	}

	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	public ModelMap trade(@PathVariable Long clientId) {
		// Retrieve client by id
		Client cl = clients.findOne(clientId);
		if (null == cl) {
			throw new IllegalArgumentException("No Client found for id " + clientId);
		}

		eventBus.notify("trade.execute", Event.wrap(tradeServer.nextTrade()));

		// Update trade count
		cl = clients.save(cl.setTradeCount(cl.getTradeCount() + 1));

		ModelMap result = new ModelMap();
		result.addAttribute("clientName", cl.getName());
		result.addAttribute("tradeCount", cl.getTradeCount());
		return result;
		// Return result
//		return "Hello " + cl.getName() + "! You now have " + cl.getTradeCount() + " trades.";
	}

}
