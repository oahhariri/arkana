# frozen_string_literal: true

RSpec.describe TemplateArguments do
  let(:config) { Config.new(YAML.load_file("spec/fixtures/arkana-fixture.yml")) }
  let(:salt) { SaltGenerator.generate }

 
  let(:environment_secrets) do
    Encoder.encode!(
      keys: config.environment_keys,
      salt: salt,
      current_flavor: config.current_flavor,
      environments: config.environments,
    )
  end

  let(:global_secrets) do
    Encoder.encode!(
      keys: config.global_secrets,
      salt: salt,
      current_flavor: config.current_flavor,
      environments: config.environments,
    )
  end
 
  let(:global_Info) do
    Encoder.encodeNo!(
      keys: config.global_Info, 
      current_flavor: config.current_flavor,
      environments: config.environments,
    )
  end

  subject do
    TemplateArguments.new(
      environment_secrets: environment_secrets,
      global_secrets: global_secrets,
      global_Info: global_Info,
      config: config,
      salt: salt,
    )
  end

  before do
    config.all_keys.each do |key|
      allow(ENV).to receive(:[]).with(key).and_return("value")
    end
  end

  describe ".new" do
    it "should have all the properties properly assigned" do
      expect(subject.instance_variable_get(:@environments)).to eq config.environments
      expect(subject.instance_variable_get(:@salt)).to eq salt
      expect(subject.instance_variable_get(:@environment_secrets)).to eq environment_secrets
      expect(subject.instance_variable_get(:@global_secrets)).to eq global_secrets
      expect(subject.instance_variable_get(:@global_Info)).to eq config.global_Info
      expect(subject.instance_variable_get(:@import_name)).to eq config.import_name
      expect(subject.instance_variable_get(:@pod_name)).to eq config.pod_name
      expect(subject.instance_variable_get(:@namespace)).to eq config.namespace
      expect(subject.instance_variable_get(:@swift_declaration_strategy)).to eq config.swift_declaration_strategy
      expect(subject.instance_variable_get(:@should_generate_unit_tests)).to eq config.should_generate_unit_tests
    end
  end

  describe ".environment_protocol_secrets" do
    let(:environment) { "Debug" }
    subject { super().environment_protocol_secrets(environment) }

    it "should return only the secrets specific to the given environment" do
      expect(subject.count).to eq 2
      expect(subject.map(&:key)).to eq ["ServiceKey#{environment}", "Server#{environment}"]
    end
  end

  describe ".generate_test_secret" do
    let(:key) { "Lorem" }
    subject { super().generate_test_secret(key: key) }

    it "should return a secret of type string" do
      expect(subject.key).to eq key
      expect(subject.protocol_key).to eq key
      expect(subject.encoded_value).to_not eq key
      expect(subject.type).to eq :string
    end
  end
end
