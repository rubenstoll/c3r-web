<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" 
    xmlns:xlink="http://www.w3.org/1999/xlink#"
    xmlns:ifc="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance#"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"   
    xmlns:ex="urn:iso10303-28:ex"   
    	>

<!-- resourceURL must be = ifcLoadedFile HOW? given as parameter -->

    <xsl:variable name="resourceURL">
        <xsl:text>E:/fev2008/IFCdata/testIfc1.xml</xsl:text>
    </xsl:variable>
    
    <xsl:variable name="nsPrefix">ifc</xsl:variable>
    <xsl:variable name="ifcSensNode" select="/ex:iso_10303_28/ex:uos/node()"/>
    <xsl:variable name="keyConcept" select="document('queryConcepts.xml')//concept"/>
    <xsl:variable name="valueConcept" select="$keyConcept/."/>
          
       <!-- define the doc as rdf -->
    <xsl:template match="/">
        <!-- check if we need to declare NS specially -->
        <rdf:RDF 
            xmlns:rdf ="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
            xmlns:xsd ="http://www.w3.org/2001/XMLSchema#"    
            xmlns:ifc ="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#"
            xmlns     ="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs#" 
            xml:base ="http://rainbow.essi.fr/~anastasiya/data/ifc.rdfs-instances"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance#">   
            
            <rdf:Description
                rdf:about="{$resourceURL}">               
                <xsl:call-template name="noEx"></xsl:call-template>        
                
                <xsl:call-template name="toMinuscule"></xsl:call-template>
            </rdf:Description>
        </rdf:RDF>     
    </xsl:template>
    
    <xsl:template name="noEx" match="/ex:iso_10303_28/ex:uos"/>
        

      
    <!-- transformation to declare properties as minuscule -->
    <xsl:template match="*[not(starts-with(name(.),'ifc:Ifc'))]" name="toMinuscule">
            <xsl:element name="{translate(name(),'ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞ', 'abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþ')}">
                <xsl:apply-templates select="@*|node()"/>
            </xsl:element>        
    </xsl:template>       

     
    <!-- Copy  elements from the EXTRACTION list, putting them in a namespace. >
    <xsl:template match="*" name="useful">
        <xsl:for-each select="$valueConcept">
            <xsl:if test="node()=$valueConcept">
                <xsl:element name="{$nsPrefix}:{node()}">
                    <xsl:value-of select="."/>
                </xsl:element>   
            </xsl:if>                        
        </xsl:for-each>        
    </xsl:template-->
    
</xsl:stylesheet>
