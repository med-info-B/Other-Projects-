package server;

import protocol.ChatEvents;
import session.RoomEvents;

public interface ChatModelEvents extends ChatEvents, RoomEvents{}
