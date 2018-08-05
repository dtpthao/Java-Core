<?xml version="1.0" encoding="UTF-8"?>
<!-- XSL transformation -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:practice7="http://knure.kharkov.ua/jt/practice7" version="1.0">

	<xsl:template match="/practice7:Bank">
		<html>
			<head>
				<title>Bank Deposits</title>
				<style type="text/css">
					td {border: 1px solid black; padding: 5px;}
					table {border: 2px solid black;}
				</style>
			</head>
			<body>
				<table>
					
					<xsl:apply-templates select="Deposit" />
				</table>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="Deposit">
	<tr>
			<td colspan="2">~~~~~~~~~~~~</td>
	</tr>
	<xsl:apply-templates select="Name" />
	<xsl:apply-templates select="Country" />
	<xsl:apply-templates select="Depositor" />
	<xsl:apply-templates select="AccountID" />
	<xsl:apply-templates select="Type" />
	<xsl:apply-templates select="AmountOnDeposit" />
	<xsl:apply-templates select="Profitability" />
	<xsl:apply-templates select="TimeConstraints" />
	</xsl:template>

	<xsl:template match="Name">
		<tr>
			<td>Name: </td>
			<td>
				<xsl:value-of select="current()" />
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="Country">
		<tr>
			<td>Country: </td>
			<td><xsl:value-of select="current()" />
		</td>
	</tr>
	</xsl:template>
	
	<xsl:template match="Depositor">
		<tr>
			<td>Depositor: </td>
			<td><xsl:value-of select="current()" />
		</td>
	</tr>
	</xsl:template>
	
	<xsl:template match="AccountID">
		<tr>
		<td>AccountID: </td>
		<td><xsl:value-of select="current()" /></td>
	</tr>
	</xsl:template>
	
	<xsl:template match="Type">
		<tr>
		<td>Type: </td>
		<td><xsl:value-of select="current()" /></td>
	</tr>
	</xsl:template>
	
	<xsl:template match="AmountOnDeposit">
		<tr>
		<td>Amount on deposit: </td>
		<td><xsl:value-of select="current()" /></td>
	</tr>
	</xsl:template>
	
	<xsl:template match="Profitability">
		<tr>
		<td>Profitability: </td>
		<td><xsl:value-of select="current()" /></td>
	</tr>
	</xsl:template>
	
	<xsl:template match="TimeConstraints">
		<tr>
		<td>Time Constraints: </td>
		<td><xsl:value-of select="current()" /></td>
	</tr>
	</xsl:template>
	

</xsl:stylesheet>