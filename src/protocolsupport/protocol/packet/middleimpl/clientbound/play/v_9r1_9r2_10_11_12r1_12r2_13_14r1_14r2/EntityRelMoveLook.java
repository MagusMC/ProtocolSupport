package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleEntityRelMoveLook;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.serializer.VarNumberSerializer;

public class EntityRelMoveLook extends MiddleEntityRelMoveLook {

	public EntityRelMoveLook(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public void writeToClient() {
		ClientBoundPacketData entityrelmovelook = codec.allocClientBoundPacketData(PacketType.CLIENTBOUND_PLAY_ENTITY_REL_MOVE_LOOK);
		VarNumberSerializer.writeVarInt(entityrelmovelook, entityId);
		entityrelmovelook.writeShort(relX);
		entityrelmovelook.writeShort(relY);
		entityrelmovelook.writeShort(relZ);
		entityrelmovelook.writeByte(yaw);
		entityrelmovelook.writeByte(pitch);
		entityrelmovelook.writeBoolean(onGround);
		codec.write(entityrelmovelook);
	}

}
