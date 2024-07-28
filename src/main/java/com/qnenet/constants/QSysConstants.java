package com.qnenet.constants;

public class QSysConstants {

    // 19.xtf.its 100,000,000,000
    // zz.storesPrev.storesPrev 2,821,109,907,455
//    public static final long QNE_GLOBAL_ID_BASE = 100_000_000_000L; // 100,000,000 1NJCHS 1n.jc.hs

    public static final long QNE_GLOBAL_ID_BASE =   80_603_140_213L; // base 36 = 1111 1111
    public static final long QNE_GLOBAL_ID_MAX = 2_821_109_907_455L; // base 36 = zzzz zzzz Can be increased if necessary


    public static final int NODE_INSTALL_IDX = 0;


    public static final String APP_NAME = "QuickNEasy";


    public static final String SESSION_INFO = "session.info";
    public static final String MAIN_LAYOUT = "main.laayout";


    public static final String SYSTEM_STATUS_LIST = "node.status.list";

    public static final String NODE_UUID = "node.uuid";
    public static final String NODE_NAME = "node.name";


    public static final String IS_NEW_INSTALL = "is.new.install";
    public static final String JAVA_JAR = "javajar";
    public static final String JAVA = "java";
    public static final String USER = "user";

    public static final String USER_HOME_PATH = "user.home.path";
    public static final String INSTALL_PATH = "install.path";
    public static final String JAVA_PATH = "java.path";
    public static final String SYSTEM_TMP_PATH = "system.tmp.path";

    public static final String MACHINE_CPU_COUNT = "machine.cpu.count";
    public static final String MACHINE_CPU_SPEED = "machine.cpu.speed";
    public static final String MACHINE_FULLY_QUALIFIED_DOMAIN_NAME = "machine.fully.qualified.domain.name";
    public static final String MACHINE_HOST_NAME = "machine.host.name";
    public static final String MACHINE_IP_ADDRESS = "machine.ip.address";
    public static final String MACHINE_SWAP_MEMORY = "machine.swap.memory";
    public static final String MACHINE_TOTAL_MEMORY = "machine.total.memory";
    public static final String PLATFORM_EXEC_SUFFIX = "platform.exec.suffix";
    public static final String PLATFORM_HAS_MULTIPLE_PROCESSORS = "platform.has.multiple.processors";
    public static final String PLATFORM_NAME = "platform.name";
    public static final String PLATFORM_PATH_SEPARATOR = "platform.path.separator";
    public static final String SYSTEM_LOCALE = "system.locale";
    public static final String SYSTEM_USER_NAME = "system.user.name";
    public static final String INSTALLER_DIRECTORY = "installer.directory";
    public static final String INSTALLER_PATH_NAME = "installer.path.name";
    public static final String PLATFORM_INSTALLER_PREFIX = "platform.installer.prefix";
    public static final String OSX_MAJOR_VERSION = "osx.major.version";
    public static final String ROUTE_INVITE = "osx.version";
    public static final String WINDOWS_OS_NAME = "windows.os.name";
    public static final String SSL_PRIVATE_KEY_BYTES = "ssl.private.key.bytes";
    public static final String SSL_PUBLIC_KEY_BYTES = "ssl.public.key.bytes";
    //    public static final String NOISE_PRIVATE_KEY_BYTES = "noise.private.key.bytes";
//    public static final String NOISE_PUBLIC_KEY_BYTES = "noise.public.key.bytes";
    public static final String NOISE_KEY_PAIR = "noise.keypair";
    public static final String BIG_PWD = "master.password";
    public static final String PASSWORDS_PIN = "passwords.pin";
    public static final String WAN_IP_ADDRESS = "wan.ip.address";
    public static final String LAN_IP_ADDRESS = "lan.ip.address";
    public static final String TEST_IP_ADDRESS = "test.ip.address";
    public static final String DISCOVERY_SERVER_LIST_STR = "discovery.servers";
    public static final String IS_DISCOVERY_SERVER = "is.discovery.server";
    public static final String PORT = "server.port";
    public static final String LITTLE_PWD = "little.pwd";
    public static final String END_POINT = "end.point";
//    public static final String BASE_END_POINT_ADDR_PAIR = "base.end.point";
    public static final String MEMBER_ACCESS_KEY_LIST = "endpoint.access.keylist";
    public static final String IS_HEADLESS = "is.headless";
    public static final String KEY_STORE_PWD = "key.store.pwd";

//    public static final String NET_MSG_RESPONSE_STATUS_OK = "status.ok";
//    public static final String NET_MSG_RESPONSE_STATUS_WRONG_NODE_TYPE = "status.wrong.node.type";

    public static final int NET_MSG_RESPONSE_STATUS_OK = 0;
    public static final int NET_MSG_RESPONSE_STATUS_WRONG_NODE_TYPE = 1;
    public static final int NET_MSG_RESPONSE_STATUS_WRONG_NET_MSG_TYPE = 2;


    public static final String DEBUG_RESTART = "qne.is.debug.restart";

    public static final String ROOT_KEYSTORE_DIR_NAME = "keystoreRootCert";
    public static final String INTER_KEYSTORE_DIR_NAME = "keystoreInterCerts";
    public static final String INTERCERT_ALIAS_PREFIX = "qneintercert-";

    public static final String END_CERT_EC_NAME_CURVE = "prime192v1";
    public static final String END_CERT_KEY_ALGORITHM = "ECDSA";
    public static final String END_CERT_SIGNATURE_ALGORITHM = "SHA256withECDSA";
    public static final String END_CERT_BC_PROVIDER = "BC";




    ////////// Integer props //////////////////////////////////////////////////////////////////////

    public static final Integer SYSTEM_STATUS_NEW_INSTALL = 1;

    public static final int MEMBER_TYPE_NORMAL = 1;
    public static final int MEMBER_TYPE_DISCOVERY_SERVER = 2;

    ////////// Long props //////////////////////////////////////////////////////////////////////

    public static final Long BASE_END_POINT_MEMBER_ID = -1L;

//    public static final byte BYTES_SERIALIZED_AND_ENCRYPTED = 1;
//    public static final byte BYTES_SERIALIZED_ONLY = 2;
//    public static final byte BYTES_ENCRYPTED_ONLY = 3;
//    public static final byte BYTES_ONLY = 4;

    public static final int BIT_CLEAR = 0;
    public static final int BIT_RAW_BYTES = 1;
    public static final int BIT_SERIALIZED = 2;
    public static final int BIT_ENCRYPTEDD = 3;


    public static final int FILE_TYPE_CLASS_ID = 1;


    public static final long RAF_HEADER_FILE_TYPE = 0;
    public static final long RAF_HEADER_UUID_MSB = 4;
    public static final long RAF_HEADER_UUID_LSB = 12;
    public static final long RAF_HEADER_FIRST_STORE_PTR_INCLUSIVE = 20;
    public static final long RAF_HEADER_LAST_STORE_PTR_EXCLUSIVE = 28;
    public static final long RAF_HEADER_SEGMENT_INFO_BYTES_LENGTH = 36;
    public static final long RAF_HEADER_SEGMENT_INFO_BYTES = 40;


    public static final int SEGMENT_PTR_NEXT_UUID = 44;
    public static final int SEGMENT_PTR_ADD = 92;
    public static final int SEGMENT_PTR_LAST_ENTRY = 96;


    public static final int CHANNEL_STATUS_NEW_CHANNEL = 1;
    public static final int CHANNEL_STATUS_HANDSHAKE_FIRST = 2;
    public static final int CHANNEL_STATUS_HANDSHAKE_SECOND = 3;
    public static final int CHANNEL_STATUS_HANDSHAKE_THIRD = 4;
    public static final int CHANNEL_STATUS_TRANSPORT = 9;


    public static final int CHANNEL_BUSY_ID = 1;
    public static final int CHANNEL_NOT_BUSY_ID = 2;


    public static final byte CONTENT_TYPE_PLAIN_BYTES_ECHO = 1;
    public static final byte CONTENT_TYPE_PLAIN_BYTES_NET_MSG = 2;

    public static final byte KEEP_ALIVE_FALSE = 100;
    public static final byte KEEP_ALIVE_TRUE = 101;

    public static final long SYSTEM_EP_ID_VALUE = 9999L;
    public static final short SYSTEM_EP_IDX = -1;

//    public static final String PORT_INIT_RESULT_KEY = "port.init.result";
    public static final String PROTOCOL_TCP = "TCP";
    public static final String PROTOCOL_UDP = "UDP";
    public static final String PORT_MAP_DESCRIPTION = "QNE";

//    public static final int PORT_NEEDS_MANUAL_SETUP_GATEWAY_FOUND = 1;
//    public static final int PORT_NEEDS_MANUAL_SETUP_GATEWAY_NOT_FOUND = 2;
//    public static final int PORT_SET_OK = 3;
//
//    public static final int PORT_INIT_FAILED = 9;


    public static final int ENDPOINT_TYPE_FIRST = 1;
    public static final int ENDPOINT_TYPE_MEMBER = 2;

    public static final String DISCOVERY_SERVER = "discovery.server";


//    public static final long STORE_CURRENT_SEGMENT_LAST_ITEM_PTR = -1L;
//    public static final long SEGMENT_LAST_ITEM_PTR = -2L;
}
