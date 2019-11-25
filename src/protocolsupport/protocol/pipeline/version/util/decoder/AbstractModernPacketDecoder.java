package protocolsupport.protocol.pipeline.version.util.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import protocolsupport.protocol.ConnectionImpl;

public abstract class AbstractModernPacketDecoder extends AbstractPacketDecoder {

	public AbstractModernPacketDecoder(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf input) throws Exception {
		if (!input.isReadable()) {
			return;
		}
		try {
			decodeAndTransform(input);
			if (input.isReadable()) {
				throw new DecoderException("Did not read all data from packet, bytes left: " + input.readableBytes());
			}
		} catch (Exception e) {
			throwFailedTransformException(e, input);
		}
	}

}
