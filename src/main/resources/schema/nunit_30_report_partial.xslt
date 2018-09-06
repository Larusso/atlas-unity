<?xml version="1.0" encoding="UTF-8" ?>
<!--
  ~ Copyright 2018 Wooga GmbH
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~       http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~
  -->

<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified">
    <xs:element name="test-run" type="testRunType"/>

    <xs:complexType name="testRunType">
        <xs:sequence>
            <xs:element name="test-suite"/>
            <xs:any minOccurs="0"/>
        </xs:sequence>
        <xs:anyAttribute/>
    </xs:complexType>
</xs:schema>