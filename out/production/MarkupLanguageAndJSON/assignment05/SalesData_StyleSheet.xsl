<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet  version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

    <xsl:template match="/CottonDesk">
        <HTML>
            <HEAD>
                <TITLE>
                    Sales Data
                </TITLE>
            </HEAD>
            <BODY>
                <H1>
                    Sales Data
                </H1>
                <table border="1">
                    <tr bgcolor = "lightgreen">
                        <th>Name</th>
                        <th>Age</th>
                        <th>Product</th>
                        <th>Commission</th>
                    </tr>
                    <xsl:apply-templates/>
                </table>
            </BODY>
        </HTML>
    </xsl:template>

    <xsl:template  match="SalesPerson">
        <tr>
            <td >
                <xsl:value-of select="Name"/>
            </td>
            <td>
                <xsl:apply-templates select="Age"/>
            </td>
            <td>
                <xsl:apply-templates select="Product"/>
            </td>
            <td>
                <xsl:apply-templates select="Sales"/>
            </td>
        </tr>
    </xsl:template>


</xsl:stylesheet>
