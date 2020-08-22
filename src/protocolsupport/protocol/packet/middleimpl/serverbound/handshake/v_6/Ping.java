package protocolsupport.protocol.packet.middleimpl.serverbound.handshake.v_6;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.ServerBoundPacketData;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.protocol.serializer.VarNumberSerializer;
import protocolsupport.protocol.utils.ProtocolVersionsHelper;

public class Ping extends ServerBoundMiddlePacket {

	public Ping(MiddlePacketInit init) {
		super(init);
	}

	protected String hostname;
	protected int port;

	@Override
	protected void readClientData(ByteBuf clientdata) {
		clientdata.readUnsignedByte();
		clientdata.readUnsignedByte();
		StringSerializer.readShortUTF16BEString(clientdata, Short.MAX_VALUE);
		clientdata.readUnsignedShort();
		clientdata.readUnsignedByte();
		hostname = StringSerializer.readShortUTF16BEString(clientdata, Short.MAX_VALUE);
		port = clientdata.readInt();
	}

	@Override
	protected void writeToServer() {
		ServerBoundPacketData setprotocol = ServerBoundPacketData.create(PacketType.SERVERBOUND_HANDSHAKE_START);
		VarNumberSerializer.writeVarInt(setprotocol, ProtocolVersionsHelper.LATEST_PC.getId());
		StringSerializer.writeVarIntUTF8String(setprotocol, hostname);
		setprotocol.writeShort(port);
		VarNumberSerializer.writeVarInt(setprotocol, 1);
		codec.read(setprotocol);

		codec.read(ServerBoundPacketData.create(PacketType.SERVERBOUND_STATUS_REQUEST));
	}

}
