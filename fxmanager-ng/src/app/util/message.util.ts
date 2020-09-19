import { messages } from './message.constants';
export class MessageUtil {

    private static msgCache: string[] = [];

    public static getMsg(key: string, params) {
        let keyCache = key + params.toString();
        let msg: string = MessageUtil.msgCache[keyCache];
        if (msg == null) {
            msg = messages[key];
            if (msg != null) {
                if (params != null) {
                    for (let i = 0; i < params.length; i++) {
                        msg = msg.replace("?" + (i + 1), params[i]);
                    }
                }
            } else {
                msg = key;
            }
            MessageUtil.msgCache[keyCache] = msg;
        }
        return msg;
    }

}