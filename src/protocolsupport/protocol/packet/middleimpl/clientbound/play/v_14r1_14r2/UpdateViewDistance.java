package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_14r1_14r2;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleUpdateViewDistance;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.serializer.VarNumberSerializer;

public class UpdateViewDistance extends MiddleUpdateViewDistance {

	public UpdateViewDistance(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public void writeToClient() {
		ClientBoundPacketData updateviewdistance = codec.allocClientBoundPacketData(PacketType.CLIENTBOUND_PLAY_UPDATE_VIEW_DISTANCE);
		VarNumberSerializer.writeVarInt(updateviewdistance, distance);
		codec.write(updateviewdistance);
	}

}
