package protocolsupport.protocol.packet.middle.clientbound.play;

import io.netty.buffer.ByteBuf;
import protocolsupport.protocol.packet.middle.ClientBoundMiddlePacket;
import protocolsupport.protocol.serializer.ArraySerializer;
import protocolsupport.protocol.serializer.VarNumberSerializer;

public abstract class MiddleStatistics extends ClientBoundMiddlePacket {

	public MiddleStatistics(MiddlePacketInit init) {
		super(init);
	}

	protected Statistic[] statistics;

	@Override
	protected void readServerData(ByteBuf serverdata) {
		statistics = ArraySerializer.readVarIntTArray(
			serverdata, Statistic.class,
			from -> new Statistic(VarNumberSerializer.readVarInt(from), VarNumberSerializer.readVarInt(from), VarNumberSerializer.readVarInt(from))
		);
	}

	@Override
	protected void cleanup() {
		statistics = null;
	}

	public static class Statistic {
		public final int category;
		public final int id;
		public final int value;
		public Statistic(int category, int id, int value) {
			this.category = category;
			this.id = id;
			this.value = value;
		}
	}

}
