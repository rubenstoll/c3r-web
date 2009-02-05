<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"	
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" 
    xmlns:xlink="http://www.w3.org/1999/xlink#"
    xmlns:ifc="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance#"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"   
    xmlns:ex="urn:iso10303-28:ex">
    <xsl:output method="xml" encoding="UTF-8"/>
    
    <xsl:variable name="resourceURL">
        <xsl:text>C:/Documents and Settings/Anastasiya/Bureau/fev2008/IFCdata/testIfc1.xml</xsl:text>
    </xsl:variable>
    
    <xsl:variable name="keyConcept" select="document('queryConcepts.xml')//concept"/>
    <xsl:variable name="thisDoc" select="document('testIfc1.xml')//ex:iso_10303_28/ex:uos/*"/>
    <xsl:variable name="keyConcept5" select="document('queryConcepts.xml')//concept[5]"/>
    <xsl:variable name="keyConcept9" select="document('queryConcepts.xml')//concept[9]"/>

    <xsl:template match="/">

        <!-- list useful concepts -->
        the useful concepts are as follows : 
        <xsl:for-each select="$keyConcept">
            <xsl:variable name="valueConcept" select="."/>
            <xsl:value-of select="$valueConcept"/> 
            <xsl:for-each select="$thisDoc">
                <!-- DEBUG
                current useful is <xsl:value-of select="$valueConcept"/>
                current THIS node is <xsl:value-of select="name()"/>
                -->
                <xsl:if test="$valueConcept=name()">
                    <xsl:copy-of select="descendant-or-self::node()"/>

                    <!--xsl:value-of select="name()"/-->
                </xsl:if>
            </xsl:for-each>
        </xsl:for-each>
    </xsl:template>
    


    <!--xsl:template match="*">   
        <xsl:for-each select="node()">
            <xsl:variable name="isUseful">
                <xsl:value-of select="false"/>
            </xsl:variable>            
        </xsl:for-each>


        <xsl:copy>
            <xsl:variable name="valueConcept">
                <xsl:value-of select="$keyConcept/."/>        
            </xsl:variable>
            <xsl:if test="name()=$valueConcept">
                <xsl:copy-of select="."/>
            </xsl:if>
        </xsl:copy>
        <xsl:apply-templates/>
    </xsl:template-->
    

</xsl:stylesheet>


<!--xsl:element name="{name()}">
    <xsl:value-of select="."/>
</xsl:element-->