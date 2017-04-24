package com.ouzhx.common;

public enum CommandEnum {
	UNKNOW_CMD("1", "未知广告机指令"),
	RECEIVE_OK_CMD("2", "广告机通知服务器, 指令接收成功"),
	
	CONNECT_CMD("3", "广告机连接服务器指令"),
	HEART_BEAT_CMD("4", "广告机发送心跳包指令"),
	VOICE_CMD("5", "服务器发送指令, 调节广告机音量"), //0x: 客户端-服务端指令; 1x: 服务端-客户端指令
	SCREEN_CAPTURE_CMD("6", "服务器器发送指令, 对广告机频幕截图"),
	SHUT_DOWN_CMD("7", "服务发送指令, 关闭广告机"),
	REBOOT_CMD("8", "服务器发送指令, 重启广告机"),
	SLEEP_CMD("9", "服务器发送指令, 让广告机进入睡眠状态"),
	INTER_CUT_CMD("10", "服务器发送指令, 插播广告"),
	GET_DIRECTORY_CMD("11", "服务器发送指令, 获取广告机目录"),
	DELETE_FILE_CMD("12", "服务器发送指令, 删除广告机中的文件"),
	DELETE_DIRECTORY_CMD("13", "服务发送指令, 删除广告机的目录"),
	GET_DISK_SPACE_CMD("14", "服务器发送指令, 获取广告机磁盘空间");
	 
	private String cmdType;
	private String cmdContent;
	
	private CommandEnum(String cmdType, String cmdContent){
		this.cmdType = cmdType;
		this.cmdContent = cmdContent;
	}

	public String getCmdType() {
		return cmdType;
	}

	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	public String getCmdContent() {
		return cmdContent;
	}

	public void setCmdContent(String cmdContent) {
		this.cmdContent = cmdContent;
	} 
	
	public static CommandEnum getEnum(String commandType){//根据值获得实例
		CommandEnum e = null;
        for(CommandEnum ce : CommandEnum.values())
            if(ce.cmdType.equals(commandType)){
                e = ce;
                break;
            }
        return e;
	}
	
	public String toString(){
		return "{cmdType:"+ cmdType+", cmdContent: "+cmdContent+"}";
	}
}
