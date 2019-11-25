package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_14r1_14r2;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleServerDifficulty;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.serializer.MiscSerializer;

public class ServerDifficulty extends MiddleServerDifficulty {

	public ServerDifficulty(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public void writeToClient() {
		ClientBoundPacketData serverdifficulty = codec.allocClientBoundPacketData(PacketType.CLIENTBOUND_PLAY_SERVER_DIFFICULTY);
		MiscSerializer.writeByteEnum(serverdifficulty, difficulty);
		serverdifficulty.writeBoolean(locked);
		codec.write(serverdifficulty);
	}

}
