Pod::Spec.new do |spec|
spec.name = "CraftDSwiftUI"
spec.version = "1.0.0"
spec.summary = "A framework to implement Server-Driven UI quickly and easily"
spec.description = "Create dynamic form screens using Server Driven UI concepts using CraftD"
spec.homepage = "https://codandotv.gitbook.io/craftd"
spec.license = { :type => "MIT", :file => "LICENSE" }
spec.author = { "Rodrigo Vianna" => "rodrigo.vianna.oliveira@gmail.com" }
spec.platform = :ios, "15.0"
spec.swift_version = '5.0'
spec.source = { :git => "https://github.com/CodandoTV/CraftD.git", :tag => 'ios-1.0.0' }
spec.source_files = "Sources/**/*.swift"
end
