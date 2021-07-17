<?xml version="1.0" encoding="UTF-8" ?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

<body style="font-size:13pt;background-color:#EAEAEA">
    <h1 style="font-size:30pt;color:blue">Demo xml,Dom,xslt</h1>
    <xsl:for-each select="company/staff">
        <ul>
            <li>
                <xsl:value-of select="@id"/>-
                <xsl:value-of select="name"/>-
                <xsl:value-of select="role"/>-
            </li>
        </ul>
    </xsl:for-each>
</body>
</html>