package universalelectricity.components.common;

import java.util.Arrays;

import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.network.ConnectionHandler;
import universalelectricity.prefab.network.PacketManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = BasicComponents.CHANNEL, name = BasicComponents.NAME, version = UniversalElectricity.VERSION, useMetadata = true)
@NetworkMod(channels = BasicComponents.CHANNEL, clientSideRequired = true, serverSideRequired = false, connectionHandler = ConnectionHandler.class, packetHandler = PacketManager.class)
public class BCLoader
{

	@Instance(BasicComponents.CHANNEL)
	public static BCLoader instance;

	@SidedProxy(clientSide = "universalelectricity.components.client.ClientProxy", serverSide = "universalelectricity.components.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Metadata(BasicComponents.CHANNEL)
	public static ModMetadata metadata;

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		NetworkRegistry.instance().registerGuiHandler(this, new BCGuiHandler());
		BasicComponents.registerOres(0, true);
		BasicComponents.registerIngots(0, true);
		BasicComponents.registerPlates(0, true);
		BasicComponents.registerBronzeDust(0, true);
		BasicComponents.registerSteelDust(0, true);
		BasicComponents.registerBattery(0);
		BasicComponents.registerWrench(0);
		BasicComponents.registerCopperWire(0);
		BasicComponents.registerMachines(0);
		BasicComponents.registerCircuits(0);
		BasicComponents.registerMotor(0);
		BasicComponents.registerInfiniteBattery(0);
		proxy.preInit();
	}

	@Init
	public void load(FMLInitializationEvent evt)
	{
		metadata.modId = BasicComponents.CHANNEL;
		metadata.name = BasicComponents.NAME;
		metadata.description = "Universal Electricity is a modular coding framework that " + "allows the use of electricity in Minecraft. Mods which uses the Universal " + "Electricity API have the ability to communicate and be compatible with each other. ";

		metadata.url = "http://www.universalelectricity.com/";

		metadata.logoFile = "/universal_electricity.png";
		metadata.version = UniversalElectricity.VERSION + "." + UniversalElectricity.BUILD_VERSION;
		metadata.authorList = Arrays.asList(new String[] { "Calclavia" });
		metadata.credits = "Please visit the website.";
		metadata.autogenerated = false;

		proxy.init();
		BasicComponents.register(this);
	}
}
