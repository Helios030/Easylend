package com.easy.lend.model



class WiFiModel(
        var mac: String = "",
        var ip: String = "",
        var wifiStatus: String = "",
        var wifiName: String = "",
        var bssid: String = "",
        var ssid: String = "",
        var networkId: String = "",
        var speed: String = ""
) {
    override fun toString(): String {
        return "WiFiBean(mac='$mac', ip='$ip', wifiStatus='$wifiStatus', wifiName='$wifiName', bssid='$bssid', ssid='$ssid', networkId='$networkId', speed='$speed')"
    }
}